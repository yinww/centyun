package com.yinww.account.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.google.code.kaptcha.Producer;
import com.yinww.account.captcha.CaptchaAuthenticationFilter;
import com.yinww.web.core.constant.ResultEntity;

@Controller
public class IndexController {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
    private Producer captchaProducer;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "不正确的用户名和密码");
        }
        if (logout != null) {
            model.addObject("msg", "你已经成功退出");
        }
        model.setViewName("login");
        return model;
    }

	@RequestMapping("/whoim")
	@ResponseBody
	public Object whoIm() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@RequestMapping("/captcha-image")  
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {  
        ServletOutputStream out = null;
        try {
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");        
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");             
            response.setHeader("Pragma", "no-cache");              
            response.setContentType("image/jpeg");              
            String captcha = captchaProducer.createText();
            
            //将验证码存入session
            request.getSession().setAttribute(CaptchaAuthenticationFilter.SESSION_CAPTCHA_KEY, captcha);
            // create the image with the text  
            //System.out.println(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
            BufferedImage bi = captchaProducer.createImage(captcha);  
            out = response.getOutputStream();  
            // write the data out  
            ImageIO.write(bi, "jpg", out);
            out.flush();  
            log.info("*****服务端验证码：*******" + captcha);
        } catch (IOException e) {
        	log.info("*****服务端验证码异常*******" + e.getMessage());
            e.printStackTrace();
        }  
        finally {  
               try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.info("*****关流失败*****"+e.getMessage());
            }
        }  
        return null;  
    }

	/**
	 * 切换语言
	 *
	 * @param lang, zh - 中文, en - 英文
	 */
	@RequestMapping(value = "/changeLang", method = RequestMethod.POST)
	@ResponseBody
	public ResultEntity changeLanguage(HttpSession session, String lang) {
		if ("zh".equals(lang)) {
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
		} else if ("en".equals(lang)) {
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

}
