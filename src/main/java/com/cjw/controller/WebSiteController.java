package com.cjw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cjw.entity.WebSite;
import com.cjw.service.WebSiteService;
import com.cjw.util.PageUtil;
/**
 * 电影网站信息controller
 * @author cjw
 *
 */
@Controller
@RequestMapping("/webSite")
public class WebSiteController {

	@Resource
	private WebSiteService webSiteService;
	
	/**
	 * 分页查询电影网站信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/{id}")
	public ModelAndView list(@PathVariable(value="id",required=false)Integer page)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		List<WebSite> webSiteList = webSiteService.list(null, page, 20);
		Long total = webSiteService.getCount(null);
		mav.addObject("webSiteList", webSiteList);
		mav.addObject("pageCode", PageUtil.genPagination("/webSite/list", total, page, 20));
		mav.addObject("title", "电影网站列表");
		mav.addObject("mainPage", "webSite/list");
		mav.addObject("mainPageKey", "#f");
		mav.setViewName("index");
		return mav;
	}
}
