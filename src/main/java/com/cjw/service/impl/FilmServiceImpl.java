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

import com.cjw.entity.Film;
import com.cjw.repository.FilmRepository;
import com.cjw.service.FilmService;
import com.cjw.util.StringUtil;
@Service("filmService")
public class FilmServiceImpl implements FilmService {

	@Resource
	private FilmRepository filmRepository;
	
	
	public void save(Film film) {
		filmRepository.save(film);
	}


	@Override
	public List<Film> list(Film film, Integer page, Integer pageSize) {
		Pageable pageable = new PageRequest(page-1, pageSize,Sort.DEFAULT_DIRECTION.DESC,"publishDate");
		Page<Film> pageWebSite = filmRepository.findAll(new Specification<Film>() {
			
			public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(film!=null){
					if(StringUtil.isNotEmpty(film.getName())){
						predicate.getExpressions().add(cb.like(root.get("name"), "%"+film.getName().trim()+"%"));
					}
					if(film.getHot()!=null && film.getHot()==1){
						predicate.getExpressions().add(cb.equal(root.get("hot"),1));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageWebSite.getContent();
	}


	@Override
	public Long getCount(Film film) {
		Long count = filmRepository.count(new Specification<Film>() {
			
			@Override
			public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(film!=null){
					if(StringUtil.isNotEmpty(film.getName())){
						predicate.getExpressions().add(cb.like(root.get("name"), "%"+film.getName()+"%"));
					}
					if(film.getHot()!=null && film.getHot()==1){
						predicate.getExpressions().add(cb.equal(root.get("hot"),1));
					}
				}
				return predicate;
			}
		});
		return count;
	}


	@Override
	public void delete(Integer id) {
		filmRepository.delete(id);
	}


	@Override
	public Film findById(Integer id) {
		return filmRepository.findOne(id);
	}


	@Override
	public Film getLast(Integer id) {
		return this.filmRepository.getLast(id);
	}


	@Override
	public Film getNext(Integer id) {
		return this.filmRepository.getNext(id);
	}


	@Override
	public List<Film> randomList(Integer n) {
		return this.filmRepository.randomList(n);
	}

}
