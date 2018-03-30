package com.cjw.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cjw.entity.WebSiteInfo;
import com.cjw.repository.WebSiteInfoRepository;
import com.cjw.service.WebSiteInfoService;
import com.cjw.util.StringUtil;
/**
 * 电影动态信息管理service实现
 * @author cjw
 *
 */
@Service
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

	@Resource
	private WebSiteInfoRepository webSiteInfoRepository;
	
	@Override
	public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize,Sort.DEFAULT_DIRECTION.DESC,"publishDate");
		Page<WebSiteInfo> pageWebSite = webSiteInfoRepository.findAll(new Specification<WebSiteInfo>() {
			
			public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(webSiteInfo!=null){
					if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
						predicate.getExpressions().add(cb.like(root.get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageWebSite.getContent();
	}

	@Override
	public Long getCount(WebSiteInfo webSiteInfo) {
		
		Long count = webSiteInfoRepository.count(new Specification<WebSiteInfo>() {
			
			@Override
			public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(webSiteInfo!=null){
					if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
						predicate.getExpressions().add(cb.like(root.get("info"), "%"+webSiteInfo.getInfo()+"%"));
					}
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public List<WebSiteInfo> queryByFilmId(Integer filmId) {
		return this.webSiteInfoRepository.getFilmById(filmId);
	}

	@Override
	public List<WebSiteInfo> queryByWebSiteId(Integer webSiteId) {
		return this.webSiteInfoRepository.getWebSiteById(webSiteId);
	}

	@Override
	public void save(WebSiteInfo webSiteInfo) {
		webSiteInfoRepository.save(webSiteInfo);
	}

	@Override
	public void delete(Integer id) {
		webSiteInfoRepository.delete(id);
	}

}
