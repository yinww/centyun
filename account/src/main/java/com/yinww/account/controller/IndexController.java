package com.yinww.account.controller;

import java.awt.image.BufferedImage;
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
import com.yinww.account.security.CaptchaAuthenticationFilter;
import com.yinww.web.core.constant.ResultEntity;

@Controller
public class IndexController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
    private Producer captchaProducer;

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getMessage("Login.UserPasswdError", request));
        }
        if (logout != null) {
            model.addObject("msg", getMessage("Logout.Success", request));
        }
        model.setViewName("login");
        return model;
    }

	@RequestMapping("/whoim")
	@ResponseBody
	public Object whoIm() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	/**
	 * 生成验证码
	 */
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
            BufferedImage bi = captchaProducer.createImage(captcha);
            out = response.getOutputStream();
            // write the data out
            ImageIO.write(bi, "jpg", out);
            out.flush();
            log.info("*****get captcha*******" + captcha);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        } finally {
			try {
				out.close();
			} catch (Exception e) {
				log.info(e.getMessage(), e);
			}
		}
		return null;
    }

	/**
	 * 切换语言
	 *
	 * @param lang, zh - 中文, en - 英文
	 */
	@RequestMapping(value = "/change-lang", method = RequestMethod.POST)
	@ResponseBody
	public ResultEntity changeLanguage(HttpSession session, String lang) {
		if ("zh_CN".equals(lang)) {
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
		} else if ("en_US".equals(lang)) {
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
		} else {
			String[] langs = lang.split("_");
			Locale locale = new Locale(langs[0], langs[1]);
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

}
