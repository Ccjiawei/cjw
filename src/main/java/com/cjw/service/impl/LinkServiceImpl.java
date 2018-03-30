package com.cjw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cjw.entity.Link;
import com.cjw.repository.LinkRepository;
import com.cjw.service.LinkService;
/**
 * 友情链接service实现类
 * @author cjw
 *
 */
@Service
public class LinkServiceImpl implements LinkService {

	@Resource
	private LinkRepository linkRepository;
	
	public List<Link> list(Integer page, Integer pageSize) {
		return linkRepository.findAll(new PageRequest(page, pageSize)).getContent();
	}

	public Long count() {
		return linkRepository.count();
	}

	public void save(Link link) {
		linkRepository.save(link);
	}

	public void delete(Integer id) {
		linkRepository.delete(id);
	}

	@Override
	public List<Link> listAll() {
		return linkRepository.findAll();
	}

}
