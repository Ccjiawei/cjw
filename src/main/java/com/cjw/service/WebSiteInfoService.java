package com.cjw.service;

import java.util.List;

import com.cjw.entity.WebSite;
import com.cjw.entity.WebSiteInfo;

/**
 * 电影动态信息service接口
 * @author cjw
 *
 */
public interface WebSiteInfoService {

	/**
	 * 分页查询收录电影动态信息
	 * @param webSite
	 * @param page
	 * @param pageSiza
	 * @return
	 */
	public List<WebSiteInfo> list(WebSiteInfo webSiteInfo,Integer page,Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount(WebSiteInfo webSiteInfo);
	
	/**
	 * 根据电影id查询动态信息
	 * @param filmId
	 * @return
	 */
	public List<WebSiteInfo> queryByFilmId(Integer filmId);
	
	/**
	 * 根据网址id查询动态信息
	 * @param webSiteId
	 * @return
	 */
	public List<WebSiteInfo> queryByWebSiteId(Integer webSiteId);
	
	/**
	 * 保存或修改收录电影动态信息
	 * @param webSiteInfo
	 */
	public void save(WebSiteInfo webSiteInfo);
	
	/**
	 * 删除收录电影动态信息
	 * @param id
	 */
	public void delete(Integer id);
}
