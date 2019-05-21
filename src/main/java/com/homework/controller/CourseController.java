package com.homework.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.homework.pojo.TbCourse;
import com.homework.pojo.TbTeacher;
import com.homework.pojo.TbUser;
import com.homework.service.CourseService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbCourse> findAll(HttpSession session){		
		TbTeacher tbTeacher=(TbTeacher) session.getAttribute("teacher");
		return courseService.findAll(tbTeacher.getId());
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return courseService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param course
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbCourse course){
		try {
			courseService.add(course);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param course
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbCourse course){
		try {
			courseService.update(course);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbCourse findOne(Integer id){
		return courseService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			courseService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(  int page, int limit  ){
		TbCourse course=null;
		return courseService.findPage(course, page, limit);		
	}
	@RequestMapping("/searchcourse")
	public PageResult searchcourse( HttpSession session, int page, int limit  ){
//		TbUser user=(TbUser) session.getAttribute("student");
		TbUser user=new TbUser();
		user.setId(1);
		return courseService.searchcourse(user.getId(),page, limit);		
	}
	
}
