package com.cjw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 电影controller类
 * @author cjw
 *
 */
import org.springframework.web.servlet.ModelAndView;

import com.cjw.entity.Film;
import com.cjw.service.FilmService;
import com.cjw.service.WebSiteInfoService;
import com.cjw.util.PageUtil;

@Controller
@RequestMapping("/film")
public class FilmController {

	@Resource
	private FilmService filmService;
	@Resource
	private WebSiteInfoService webSiteInfoService;
	
	/**
	 * 首页条件查询电影信息
	 * @param s_film
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search")
	public ModelAndView serach(@Valid Film s_film,BindingResult bindingResult) throws Exception{
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()){
			mav.addObject("error",bindingResult.getFieldError().getDefaultMessage());
			mav.addObject("title", "首页");
			mav.addObject("mainPage", "film/indexFilm");
			mav.addObject("mainPageKey", "#f");
			mav.setViewName("index");
		}else{
			List<Film> filmList = filmService.list(s_film,1,32);
			mav.addObject("filmList",filmList);
			mav.addObject("title", s_film.getName());
			mav.addObject("mainPage", "film/result");
			mav.addObject("mainPageKey", "#f");
			mav.addObject("s_film", s_film);
			mav.addObject("total", filmList.size());
			mav.setViewName("index");
		}
		return mav;
	}
	
	/**
	 * 分页查询电影信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/{id}")
	public ModelAndView list(@PathVariable(value="id",required=false)Integer page)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		List<Film> filmList = filmService.list(null, page, 20);
		Long total = filmService.getCount(null);
		mav.addObject("filmList", filmList);
		mav.addObject("pageCode", PageUtil.genPagination("/film/list", total, page, 20));
		mav.addObject("title", "电影列表");
		mav.addObject("mainPage", "film/list");
		mav.addObject("mainPageKey", "#f");
		mav.setViewName("index");
		return mav;
	}
	
	/**
	 * 根据id获取电影详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{id}")
	public ModelAndView view(@PathVariable(value="id")Integer id)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		Film film = filmService.findById(id);
		mav.addObject("film", film);
		mav.addObject("title", film.getTitle());
		//随机n条电影信息
		mav.addObject("randomFilmList",filmService.randomList(8));
		//显示电影动态信息
		mav.addObject("webSiteInfoList", webSiteInfoService.queryByFilmId(id));
		//上下页数据
		mav.addObject("pageCode", this.getUpOrDownPageCode(filmService.getLast(id), filmService.getNext(id)));
		mav.addObject("mainPage", "film/view");
		mav.addObject("mainPageKey", "#f");
		mav.setViewName("index");
		return mav ;
	}
	
	/**
	 * 获取上一篇和下一篇电影
	 * @param lastFilm
	 * @param nextFilm
	 * @return
	 */
	private String getUpOrDownPageCode(Film lastFilm,Film nextFilm){
		StringBuffer sb = new StringBuffer();
		if(lastFilm==null || lastFilm.getId()==null){
			sb.append("<p>上一篇：没有了</p>");
		}else{
			sb.append("<p>上一篇：<a href='/film/"+lastFilm.getId()+"'>"+lastFilm.getTitle()+"</a></p>");
		}
		if(nextFilm==null || nextFilm.getId()==null){
			sb.append("<p>下一篇：没有了</p>");
		}else{
			sb.append("<p>下一篇：<a href='/film/"+nextFilm.getId()+"'>"+nextFilm.getTitle()+"</a></p>");
		}
		return sb.toString();
		
	}
	
}
