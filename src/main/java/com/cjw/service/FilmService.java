package com.cjw.service;

import java.util.List;

import com.cjw.entity.Film;
/**
 * 电影service接口
 * @author cjw
 *
 */
public interface FilmService {

	/**
	 * 添加电影信息
	 * @param film
	 */
	public void save(Film film);
	
	/**
	 * 分页查询电影信息
	 * @param webSite
	 * @param page
	 * @param pageSiza
	 * @return
	 */
	public List<Film> list(Film film,Integer page,Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount(Film film);
	
	/**
	 * 删除电影信息
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 查询单条电影信息
	 * @param id
	 * @return
	 */
	public Film findById(Integer id);
	
	/**
	 * 获取上一篇电影信息
	 * @param id
	 * @return
	 */
	public Film getLast(Integer id);
	
	/**
	 * 获取下一篇电影信息
	 * @param id
	 * @return
	 */
	public Film getNext(Integer id);
	
	/**
	 * 随机获取n个电影
	 * @param n
	 * @return
	 */
	public List<Film> randomList(Integer n);
}
