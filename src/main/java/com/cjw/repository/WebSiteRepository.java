package com.cjw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cjw.entity.Link;
import com.cjw.entity.WebSite;
/**
 * 网址管理数据层接口repository
 * @author cjw
 *
 */
@Repository
public interface WebSiteRepository extends JpaRepository<WebSite, Integer>,JpaSpecificationExecutor<WebSite>{

}
