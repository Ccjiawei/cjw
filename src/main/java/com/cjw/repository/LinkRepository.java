package com.cjw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjw.entity.Link;
/**
 * 友情链接数据层接口repository
 * @author cjw
 *
 */
@Repository
public interface LinkRepository extends JpaRepository<Link, Integer>{

}
