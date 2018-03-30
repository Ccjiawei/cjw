package com.cjw.run;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cjw.entity.Film;
import com.cjw.service.FilmService;
import com.cjw.service.LinkService;
import com.cjw.service.WebSiteInfoService;
import com.cjw.service.WebSiteService;
/**
 * 为前台展示收集数据
 * @author cjw
 *
 */
@Component("startupRunner")
public class StartupRunner implements CommandLineRunner,ServletContextListener{

	@Resource
	private ServletContext application=null;
	@Resource
	private FilmService filmService;
	@Resource
	private WebSiteService webSiteService; 
	@Resource
	private WebSiteInfoService webSiteInfoService;
	@Resource
	private LinkService linkService;
	
	@Override
	public void run(String... arg0) throws Exception {
		loadData();
	}

	/**
	 * 加载数据到applicaton缓存中
	 */
	public void loadData(){
		application.setAttribute("newestInfoList", webSiteInfoService.list(null, 1, 10));// 最新10条电影动态
		Film film = new Film();
		film.setHot(1);
		application.setAttribute("newestHotFilmList", filmService.list(null, 1, 10));// 获取最新10条热门电影
		application.setAttribute("newestIndexHotFilmList", filmService.list(film, 1, 32)); // 获取最新32条热门电影 首页显示用到
		application.setAttribute("newestWebSiteList", webSiteService.newestList(0, 10)); // 获取最新10条电影网站收录
		application.setAttribute("newestFilmList", filmService.list(null, 1, 10)); // 获取最新10条电影信息
		application.setAttribute("linkList", linkService.listAll()); // 查询所有友情链接
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		application=sce.getServletContext();
	}

}
