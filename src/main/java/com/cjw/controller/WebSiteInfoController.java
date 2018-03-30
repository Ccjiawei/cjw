package com.cjw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cjw.entity.WebSiteInfo;
import com.cjw.service.WebSiteInfoService;
import com.cjw.util.PageUtil;
/**
 * 电影动态信息controller
 * @author cjw
 *
 */
@Controller
@RequestMapping("/webSiteInfo")
public class WebSiteInfoController {

	@Resource
	private WebSiteInfoService webSiteInfoService;
	
	/**
	 * 分页查询电影动态信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/{id}")
	public ModelAndView list(@PathVariable(value="id",required=false)Integer page)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(null, page, 20);
		Long total = webSiteInfoService.getCount(null);
		mav.addObject("webSiteInfoList", webSiteInfoList);
		mav.addObject("pageCode", PageUtil.genPagination("/webSiteInfo/list", total, page, 20));
		mav.addObject("title", "电影动态信息列表");
		mav.addObject("mainPage", "webSiteInfo/list");
		mav.addObject("mainPageKey", "#f");
		mav.setViewName("index");
		return mav;
	}
}
