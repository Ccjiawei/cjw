package com.cjw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 根路径及其他请求处理
 * @author cjw
 *
 */
import org.springframework.web.servlet.ModelAndView;
@Controller
public class IndexController {

	@RequestMapping("/")
	public ModelAndView root(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("title","首页");
		mav.addObject("mainPage","film/indexFilm");
		mav.addObject("mainPageKey","#f");
		mav.setViewName("index");
		return mav;
	}
	
	/**
	 * 登陆请求跳转
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "/login";
	}
	
	/**
	 * 进入后台 管理请求
	 * @return
	 */
	@RequestMapping("/admin")
	public String toAdmin(){
		return "/admin/main";
	}
	
	/**
	 * 关于本站
	 * @return
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("title","关于本站");
		mav.addObject("mainPage","common/aboutMe");
		mav.addObject("mainPageKey","#a");
		mav.setViewName("index");
		return mav;
	}
}
