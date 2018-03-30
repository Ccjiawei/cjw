package com.cjw.service;

import java.util.List;

import com.cjw.entity.WebSite;

/**
 * 网址管理service接口
 * @author cjw
 *
 */
public interface WebSiteService {
	
	/**
	 * 查询单条电影网址信息
	 * @param id
	 * @return
	 */
	public WebSite findById(Integer id);

	/**
	 * 分页查询收录电影网址
	 * @param webSite
	 * @param page
	 * @param pageSiza
	 * @return
	 */
	public List<WebSite> list(WebSite webSite,Integer page,Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount(WebSite webSite);
	
	/**
	 * 保存或修改收录电影网址
	 * @param webSite
	 */
	public void save(WebSite webSite);
	
	/**
	 * 删除收录电影网址
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 获取最新电影网站收录
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<WebSite> newestList(Integer page, Integer pageSize);
}
