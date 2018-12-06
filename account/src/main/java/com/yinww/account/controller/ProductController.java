package com.yinww.account.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Product;
import com.yinww.account.service.ProductService;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.DataTableResult;
import com.yinww.util.CommonUtil;
import com.yinww.web.core.constant.AppConstant;
import com.yinww.web.core.constant.ResultEntity;
import com.yinww.web.core.exception.BadRequestException;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/index.html")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/product/index.html"));
        model.setViewName("product/product-index");
        return model;
	}

	@RequestMapping(value = "/products")
	@ResponseBody
	public Object getProducts(@ModelAttribute DTRequestParams dtParams) {
		PageInfo<Product> products = productService.getProducts(dtParams);
        return new DataTableResult<Product>(products, dtParams.getDraw());
	}

	@RequestMapping(value = "/add.html")
	public ModelAndView add() {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/product/index.html"));
        model.setViewName("product/product-add");
        return model;
	}

	@RequestMapping(value = "/edit.html")
	public ModelAndView edit(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/product/index.html"));
		Product product = productService.getProductById(id);
		model.addObject("product", product);
        model.setViewName("product/product-edit");
        return model;
	}

	@RequestMapping(value = "/view.html")
	public ModelAndView view(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/product/index.html"));
		Product product = productService.getProductById(id);
		model.addObject("product", product);
        model.setViewName("product/product-view");
        return model;
	}

	@RequestMapping(value = "/save-product", method = RequestMethod.POST)
	@ResponseBody
	public Object saveProduct(Product product, HttpServletRequest request) {
		try {
			productService.saveProduct(product);
		} catch (BadRequestException e) {
			log.error(e.getMessage(), e);
			ResultEntity result = new ResultEntity();
			result.setData(getMessage(e.getMessage(), request));
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ResultEntity result = new ResultEntity();
			result.setData(e.getMessage());
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			return result;
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

	@RequestMapping(value = "/delete-product", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteProduct(String ids, HttpServletRequest request) {
		if(!CommonUtil.isEmpty(ids)) {
			try {
				List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
				productService.deleteProduct(CommonUtil.strings2Longs(list));
			} catch (BadRequestException e) {
				log.error(e.getMessage(), e);
				ResultEntity result = new ResultEntity();
				result.setData(getMessage(e.getMessage(), request));
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				return result;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				ResultEntity result = new ResultEntity();
				result.setData(e.getMessage());
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				return result;
			}
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 转换日期
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}
}
