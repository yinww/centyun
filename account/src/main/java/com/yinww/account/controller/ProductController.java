package com.yinww.account.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Product;
import com.yinww.account.service.ProductService;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.DataTableResult;
import com.yinww.util.CommonUtil;
import com.yinww.web.core.constant.AppConstant;
import com.yinww.web.core.constant.ResultEntity;

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
	public ModelAndView edit(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/product/index.html"));
		Product product = productService.getProductById(id);
		model.addObject("product", product);
        model.setViewName("product/product-edit");
        return model;
	}

	@RequestMapping(value = "/view.html")
	public ModelAndView view(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/product/index.html"));
		Product product = productService.getProductById(id);
		model.addObject("product", product);
        model.setViewName("product/product-view");
        return model;
	}

	@RequestMapping(value = "/save-product", method = RequestMethod.POST)
	@ResponseBody
	public Object saveProduct(Product product) {
		try {
			productService.saveProduct(product);
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
	public Object deleteProduct(String ids) {
		if(!CommonUtil.isEmpty(ids)) {
			try {
				List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
				productService.deleteProduct(list);
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
}
