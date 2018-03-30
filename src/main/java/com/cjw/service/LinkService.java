package com.cjw.service;

import java.util.List;

import com.cjw.entity.Link;

/**
 * 友情 链接service接口
 * @author cjw
 *
 */
public interface LinkService {

	/**
	 * 分页查询
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Link> list(Integer page,Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long count();
	
	/**
	 * 保存或修改友情链接
	 * @param link
	 */
	public void save(Link link);
	
	/**
	 * 删除友情链接
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 查询所有友情链接
	 * @return
	 */
	public List<Link> listAll();
}
