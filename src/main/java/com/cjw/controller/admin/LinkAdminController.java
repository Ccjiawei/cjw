package com.cjw.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjw.entity.Link;
import com.cjw.run.StartupRunner;
import com.cjw.service.LinkService;
/**
 * 友情链接controller类
 * @author cjw
 *
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {

	@Resource
	private LinkService linkService;
	@Resource
	private StartupRunner startupRunner;
	/**
	 * 分页查询友情链接
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public Map<String,Object> list(@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="rows",required=false)Integer rows)throws Exception{
		List<Link> linkList = linkService.list(page-1, rows);//serviceImpl中的方法是从0开始
		Long total = linkService.count();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", linkList);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 添加或修改 友情链接
	 * @param link
	 * @return
	 */
	@RequestMapping("/save")
	public Map<String,Object> save(Link link)throws Exception{
		linkService.save(link);
		startupRunner.loadData();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
	
	/**
	 * 删除友情链接
	 * @param strIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids")String strIds)throws Exception{
		String[] ids = strIds.split(",");
		for(int i=0;i<ids.length;i++){
			linkService.delete(Integer.parseInt(ids[i]));
		}
		startupRunner.loadData();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return map;
	}
}
