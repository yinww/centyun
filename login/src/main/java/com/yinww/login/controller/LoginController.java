package com.yinww.login.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.yinww.login.service.ModuleService;
import com.yinww.util.RandomGenerator;
import com.yinww.util.SHA256Util;
import com.yinww.web.core.client.AccountFeignClient;
import com.yinww.web.core.constant.AppConstant;
import com.yinww.web.core.cookie.CookieUtils;
import com.yinww.web.core.domain.Account;
import com.yinww.web.core.domain.Module;
import com.yinww.web.core.security.CaptchaAuthenticationFilter;
import com.yinww.web.core.util.EncryptUtils;

@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ModuleService moduleService;

	@Autowired
    private Producer captchaProducer;
	
	@Autowired
	private AccountFeignClient accountFeignClient;

	@Value("${home.url}")
	private String homeUrl;

    @GetMapping(value = "/getCookies")
    @ResponseBody
    public Object getCookies(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request, AppConstant.TOKEN);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("crmToken_in_crm", cookieValue);
        return result;
    }

    @GetMapping(value = "/setCookies", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object setCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.setCookie(request, response, AppConstant.TOKEN, "yinww111_crm");
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

	@RequestMapping(value = {"", "/", "/index.html"})
	public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        List<Module> modules = moduleService.getAvailableModules();
		model.addObject("modules", resortModule(modules));
        model.setViewName("index");
        return model;
	}

	@RequestMapping(value = "getAvailableModules", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Module> getAvailableModules() {
        List<Module> modules = moduleService.getAvailableModules();
        modules = resortModule(modules);
        return modules;
	}

	private List<Module> resortModule(List<Module> modules) {
		if(modules == null || modules.size() == 0) {
			return null;
		}
		List<Module> result = new ArrayList<>();
		Map<Long, Module> moduleMap = new HashMap<>();
		for (Module module : modules) {
			Long pid = module.getParentId();
			Module pModule = moduleMap.get(pid);
			if(pModule != null) {
				pModule.addChild(module);
			}
			Long id = module.getId();
			moduleMap.put(id, module);
			if(pid == 0) {
				result.add(module); // pid为0的是根节点
			}
		}
		return result;
	}

	@GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, ModelAndView model
            , HttpServletRequest request, HttpServletResponse response) {
        if (error != null) {
            model.addObject("error", "Login.UserPasswdError");
        } else if (logout != null) { // 登出, 删除cookie
        	CookieUtils.deleteCookie(request, response, AppConstant.TOKEN);
        	model.addObject("msg", "Logout.Success");
        } else {
        	Object context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        	if(context != null && context instanceof SecurityContext) {
        		SecurityContext securityContext = (SecurityContext) context;
        		Authentication authentication = securityContext.getAuthentication();
        		if(authentication != null) {
        			try {
						response.sendRedirect(homeUrl); // 如果已经登录了, 直接跳入到首页
						return null;
					} catch (IOException e) {
						e.printStackTrace();
					}
        		}
        	}
        	
        	//如果没有登录，则获取cookie中的token去查询系统登录情况，如果查到了则赋值然后自动登录，如果没有查到则进入登录界面
        	String token = CookieUtils.getCookieValue(request, AppConstant.TOKEN);
        	if (!StringUtils.isEmpty(token)) { // cookie中没有token则跳转到登录页
        		Account account = (Account) request.getSession().getAttribute(AppConstant.LOGIN_ACCOUNT);
        		if(account == null) {
        			account = accountFeignClient.getAccountByToken(token);
        			model.addObject("user", account.getUsername());
        			String sha256 = SHA256Util.getSHA256(RandomGenerator.getString(12));
        			model.addObject(CaptchaAuthenticationFilter.SESSION_KEY, "ENCD(" + sha256 + ")");
        			request.getSession().setAttribute(CaptchaAuthenticationFilter.SESSION_KEY_ENCODE, EncryptUtils.encrypt(sha256));
        		}
        	}
        }
        return "login";
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

	@RequestMapping(value = "/getAccountByToken")
	@ResponseBody
	public Object getAccountByToken(@RequestParam(value = "token") String token) {
		Account account = accountFeignClient.getAccountByToken(token);
		return account;
	}
}
