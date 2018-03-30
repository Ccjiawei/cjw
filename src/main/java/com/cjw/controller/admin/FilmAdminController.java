package com.cjw.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjw.entity.Film;
import com.cjw.run.StartupRunner;
import com.cjw.service.FilmService;
import com.cjw.service.WebSiteInfoService;
import com.cjw.util.DateUtil;
import com.cjw.util.StringUtil;
/**
 * 电影信息管理controller
 * @author cjw
 *
 */
@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {

	@Value("${imageFilePath}")
	private String imageFilePath;
	@Resource
	private FilmService filmService;
	@Resource
	private WebSiteInfoService webSiteInfoService;
	@Resource
	private StartupRunner startupRunner;
	
	/**
	 * 查询电影信息
	 * @param webSite
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public Map<String,Object> list(Film film,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows) throws Exception{
		List<Film> filmList = filmService.list(film,page, rows);//serviceImpl中的方法是从0开始
		Long total = filmService.getCount(film);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", filmList);
		map.put("total", total);
		return map;
		
	}
	
	/**
	 * 添加、修改电影信息
	 * @param film
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public Map<String,Object> addFilm(Film film,@RequestParam("imageFile")MultipartFile file)throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();//源文件名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));//后缀
			String newFileName = DateUtil.getCurrentDateStr()+suffixName;//新文件名
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));//写入的新地址
			film.setImageName(newFileName);
		}
		film.setPublishDate(new Date());
		filmService.save(film);
		startupRunner.loadData();
		map.put("success", true);
		return map;
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @param CKEditorFuncNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ckeditorUpload")
	public String ckeditorUpload(@RequestParam("upload")MultipartFile file,String CKEditorFuncNum) throws Exception{
		
		String fileName = file.getOriginalFilename();//源文件名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));//后缀
		String newFileName = DateUtil.getCurrentDateStr()+suffixName;//新文件名
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));//写入的新地址
		
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+",'"+"/static/filmImage/"+newFileName+"')");
		sb.append("</script>");
		
		return sb.toString();
	}
	
	/**
	 * 删除电影信息
	 * @param strIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids")String strIds)throws Exception{
		String[] ids = strIds.split(",");
		boolean flag = true;
		for(int i=0;i<ids.length;i++){
			Integer filmId = Integer.parseInt(ids[i]);
			if(webSiteInfoService.queryByFilmId(filmId)!=null && webSiteInfoService.queryByFilmId(filmId).size()>0){
				flag = false;
			}else{
				filmService.delete(filmId);
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		if(flag){
			map.put("success", true);
		}else{
			map.put("succes", false);
			map.put("errorInfo", "要删除的电影动态信息中存在电影信息的关联，不能删除！");
		}
		startupRunner.loadData();
		return map;
	}

	/**
	 * 查询单条电影信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById")
	public Film findById(Integer id){
		Film film = filmService.findById(id);
		return film;
	}
	
	/**
	 * 查询电影信息下拉框
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/comboList")
	public List<Film> comboList(String q)throws Exception{
		if(StringUtil.isNotEmpty(q)){
			Film film = new Film();
			film.setName(q);
			return filmService.list(film , 1, 30);
		}
		return null;
	}
}