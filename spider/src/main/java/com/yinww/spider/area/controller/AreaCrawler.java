package com.yinww.spider.area.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yinww.spider.area.vo.AreaVo;

public class AreaCrawler {
	 
    private static Gson gson = new GsonBuilder().create();
 
    private static String URL_PREFIX = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";
 
    private static int count = 0;
 
    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("areaCrawler-pool-%d").build();
    //创建线程池
    private static final ExecutorService pool     = new ThreadPoolExecutor(5, 200, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(16), namedThreadFactory,
            new ThreadPoolExecutor.AbortPolicy());
    
    private static List<AreaVo> areaVoList = new ArrayList<>();
 
    public Document getDocument(String url) throws IOException {
        try{
            Connection conn = Jsoup.connect(url).timeout(5000);
            conn.header("Accept", "*/*");
            conn.header("Accept-Encoding", "gzip, deflate, br");
            conn.header("Accept-Language", "zh-CN,zh;q=0.9");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            Connection.Response response = null;
            do {
                response = conn.execute();
                if(count > 0){
                    Document doc = Jsoup.connect(url)
                            .data("query", "Java")
                            .userAgent("Mozilla")
                            .cookie("auth", "token")
                            .timeout(3000)
                            .post();
                    return doc;
                }
            } while(response.statusCode() != 200);
            return  conn.get();
        }catch (IOException e){
            throw e;
        }
    }
 
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AreaCrawler t = new AreaCrawler();
        Document doc = null;
        do {
            try{
                doc = t.getDocument("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html");
                count = 0;
            }catch (Exception e){
                count++;
            }
        }while(doc == null);
        //获取所有的省
        List<Future>  futureList = new ArrayList<>();
        Elements provinceElements = doc.select("table.provincetable tbody tr.provincetr td");
        for(Element element : provinceElements){
            if (StringUtils.isNotBlank(element.text())) {
                AreaVo areaVo = new AreaVo();
                areaVo.setName(element.text());
                String cityUrl = URL_PREFIX + element.select("td a").get(0).attr("href");
                String code = element.select("td a").get(0).attr("href").substring(0, 2) + "0000";
                areaVo.setCode(code);
                Future future = pool.submit(new CityThread(cityUrl, areaVo));
                futureList.add(future);
            }
        }
        
        /** 等待所有的子线程执行完毕
         */
        for(Future future : futureList){
            future.get();
        }
        //关闭线程池
        pool.shutdownNow();
        Collections.sort(areaVoList, Comparator.comparing(AreaVo::getCode));
        System.out.println(gson.toJson(areaVoList));
    }
 
    private static class CityThread implements Runnable{
        String url;
        AreaVo areaVo;
        public CityThread(String url, AreaVo areaVo){
            this.url = url;
            this.areaVo = areaVo;
        }
 
        @Override
        public void run() {
            AreaCrawler t = new AreaCrawler();
            Document cityDocument = null;
            do{
                try {
                    cityDocument = t.getDocument(url);
                    count = 0;
                }catch (Exception e){
                    //e.printStackTrace();
                    count++;
                }
            }while(cityDocument == null);
            Elements cityElements = cityDocument.select("table.citytable tbody tr.citytr");
            List<AreaVo> cityList = new ArrayList<>();
            for(Element cityElement :cityElements){
                AreaVo cityVo = new AreaVo();
                String cityCode = cityElement.select("td").get(0).text().substring(0,6);
                String cityName = cityElement.select("td").get(1).text();
                cityVo.setCode(cityCode);
                cityVo.setName(cityName);
                if(cityElement.select("td a").size() > 0){
                    String countyUrl = URL_PREFIX + cityElement.select("td a").get(0).attr("href");
                    Document countyDocument = null;
                    do{
                        try{
                            countyDocument = t.getDocument(countyUrl);
                            count = 0;
                        } catch (Exception e){
                            //e.printStackTrace();
                            count++;
                        }
                    }while(countyDocument == null);
                    Elements countyElements = countyDocument.select("table tbody tr.countytr");
                    List<AreaVo> countyList = new ArrayList<>();
                    for(Element countyElement : countyElements){
                        AreaVo countyVo = new AreaVo();
                        String countyCode = countyElement.select("td").get(0).text().substring(0,6);
                        String countyName = countyElement.select("td").get(1).text();
                        countyVo.setCode(countyCode);
                        countyVo.setName(countyName);
                        countyList.add(countyVo);
                    }
                    cityVo.setChildren(countyList);
                }
                cityList.add(cityVo);
            }
            areaVo.setChildren(cityList);
            areaVoList.add(areaVo);
            System.out.println(areaVo.getName()+":"+gson.toJson(areaVo).toString());
        }
    }
}
