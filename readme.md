cent yun platform
---

cent云平台

## 接口返回码

全局返回码说明如下：

* -1      系统繁忙，此时请开发者稍候再试
* 0       请求成功

## 各模块端口规则
1. 端口在application.properties或profile中的server.port指定
2. 端口总共4位, 都以6开头
3. 第2、3位是模块, 其中10以内的被基础模块占用, 如01是login, 02是file, 03是sms, 04是mail ..., 10以上的为具体的应用模块
4. 第4位是同一个模块的不同备份, 多个备份构成应用的集群
5. 各应用的端口如下
> services  6001, 6002, 6003 ...

> account    6011, 6012, 6013 ...

> login     6021, 6022, 6023 ...

> file     6031, 6032, 6033 ...

> sms      6041, 6042, 6043 ...

> mail     6051, 6052, 6053 ...

> code     6061, 6062, 6063 ...

> .....

> cms      6211, 6112, 6113 ...

> crm      6221, 6122, 6123 ...

> spider   6231, 6232, 6233 ...


http://pv.sohu.com/cityjson?ie=utf-8
http://ip.taobao.com/instructions.html
http://ip.taobao.com/service/getIpInfo.php?ip=[ip地址字串]
1. 请求接口（GET）：

/service/getIpInfo.php?ip=[ip地址字串]

2. 响应信息：

（json格式的）国家 、省（自治区或直辖市）、市（县）、运营商

3. 返回数据格式：

{"code":0,"data":{"ip":"210.75.225.254","country":"\u4e2d\u56fd","area":"\u534e\u5317",
"region":"\u5317\u4eac\u5e02","city":"\u5317\u4eac\u5e02","county":"","isp":"\u7535\u4fe1",
"country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000",
"county_id":"-1","isp_id":"100017"}}
其中code的值的含义为，0：成功，1：失败。


icons:
https://adminlte.io/themes/AdminLTE/pages/UI/icons.html
