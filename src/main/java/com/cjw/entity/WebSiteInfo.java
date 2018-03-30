package com.cjw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 网站动态更新电影信息实体
 * @author cjw
 *
 */
@Entity
@Table(name="t_info")
public class WebSiteInfo {

	@Id
	@GeneratedValue
	private Integer id; // 编号
	
	@ManyToOne
	@JoinColumn(name="filmId") // 电影
	private Film film;
	
	@ManyToOne
	@JoinColumn(name="webSiteId") // 网站
	private WebSite webSite;
	
	@Column(length=1000) 
	private String info; // 信息
	
	@Column(length=500)
	private String url; // 具体网址
	
	private Date publishDate; //发布时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	//日期格式化
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	
}
