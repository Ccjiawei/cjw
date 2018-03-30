package com.cjw.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjw.entity.WebSiteInfo;
import com.cjw.run.StartupRunner;
import com.cjw.service.WebSiteInfoService;
/**
 * 电影动态信息管理
 * @author cjw
 *
 */
@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {

	@Resource
	private WebSiteInfoService webSiteInfoService;
	@Resource
	private StartupRunner startupRunner;
	
	@RequestMapping("/list")
	public Map<String,Object> list(WebSiteInfo webSiteInfo,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows) throws Exception{
		List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(webSiteInfo,page, rows);//serviceImpl中的方法是从0开始
		Long total = webSiteInfoService.getCount(webSiteInfo);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", webSiteInfoList);
		map.put("total", total);
		return map;
		
	}
	
	/**
	 * 添加电影动态信息
	 * @param link
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String,Object> save(WebSiteInfo webSiteInfo)throws Exception{
		webSiteInfo.setPublishDate(new Date());
		webSiteInfoService.save(webSiteInfo);
		startupRunner.loadData();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
	
	/**
	 * 删除电影动态信息
	 * @param strIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids")String strIds)throws Exception{
		String[] ids = strIds.split(",");
		for(int i=0;i<ids.length;i++){
			webSiteInfoService.delete(Integer.parseInt(ids[i]));
		}
		startupRunner.loadData();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
}
