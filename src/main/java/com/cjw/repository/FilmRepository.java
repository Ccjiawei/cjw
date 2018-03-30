package com.cjw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cjw.entity.Film;

/**
 * 电影数据层接口repository
 * @author cjw
 *
 */
@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>,JpaSpecificationExecutor<Film>{

	/**
	 * 获取上一篇电影
	 * @param id
	 * @return
	 */
	@Query(value="select * from t_film where id<?1 order by id desc limit 1",nativeQuery=true)
	public Film getLast(Integer id);
	
	/**
	 * 获取下一篇电影
	 * @param id
	 * @return
	 */
	@Query(value="select * from t_film where id>?1 order by id asc limit 1",nativeQuery=true)
	public Film getNext(Integer id);
	
	/**
	 * 随机获取n个电影
	 * @param n
	 * @return
	 */
	@Query(value="select * from t_film order by rand() limit ?1",nativeQuery=true)
	public List<Film> randomList(Integer n);
}
