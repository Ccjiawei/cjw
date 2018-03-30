package com.cjw.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjw.entity.Film;
import com.cjw.entity.WebSite;
import com.cjw.run.StartupRunner;
import com.cjw.service.WebSiteInfoService;
import com.cjw.service.WebSiteService;
import com.cjw.util.StringUtil;
/**
 * 收录电影网址
 * @author cjw
 *
 */
@RestController
@RequestMapping("/admin/webSite")
public class WebSiteAdminController {

	@Resource
	private WebSiteService webSiteService;
	@Resource
	private WebSiteInfoService webSiteInfoService;
	@Resource
	private StartupRunner startupRunner;
	
	/**
	 * 查询电影信息下拉框
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/comboList")
	public List<WebSite> comboList(String q)throws Exception{
		if(StringUtil.isNotEmpty(q)){
			WebSite webSite = new WebSite();
			webSite.setUrl(q);
			return webSiteService.list(webSite , 1, 30);
		}
		return null;
	}
	
	/**
	 * 分页查询收录电影网址信息
	 * @param webSite
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public Map<String,Object> list(WebSite webSite,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows) throws Exception{
		List<WebSite> webSiteList = webSiteService.list(webSite,page, rows);//serviceImpl中的方法是从0开始
		Long total = webSiteService.getCount(webSite);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", webSiteList);
		map.put("total", total);
		return map;
		
	}
	
	/**
	 * 添加或修改收录电影网址
	 * @param webSite
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String,Object> save(WebSite webSite)throws Exception{
		webSiteService.save(webSite);
		startupRunner.loadData();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
	
	/**
	 * 删除收录电影网址
	 * @param strIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids")String strIds)throws Exception{
		String[] ids = strIds.split(",");
		boolean flag = true;
		StringBuilder strs = new StringBuilder();//收集不可删除的网址名称信息
		for(int i=0;i<ids.length;i++){
			Integer webSiteId = Integer.parseInt(ids[i]);
			if(webSiteInfoService.queryByWebSiteId(webSiteId)!=null && webSiteInfoService.queryByWebSiteId(webSiteId).size()>0){
				flag=false;
				strs.append(webSiteService.findById(webSiteId).getName());
				strs.append(",");
			}else{
				webSiteService.delete(webSiteId);
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		if(flag){
			map.put("success", true);
		}else{
			if(strs!=null){
				String errorInfo = strs.toString().substring(0, strs.toString().lastIndexOf(","));
				map.put("success", false);
				map.put("errorInfo", "电影动态信息中存在<font color=red>"+errorInfo+"</font>，不能删除！");
			}
		}
		startupRunner.loadData();
		return map;
	}
}
