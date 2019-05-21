package com.homework.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.homework.pojo.TbTeacher;
import com.homework.pojo.TbUser;
import com.homework.service.SworkService;
import com.homework.service.TeacherService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SworkService sworkService;
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("/login")
	public Result login(@RequestBody TbTeacher teacher,HttpSession session){	
		TbTeacher loginTeacer=teacherService.login(teacher);
		if(loginTeacer!=null) {
			session.setAttribute("teacher", loginTeacer);
			return new Result(true, "登录成功");
		}
		return new Result(false, "登录失败");
	}
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbTeacher> findAll(){			
		return teacherService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return teacherService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbTeacher teacher){
		try {
			teacherService.add(teacher);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbTeacher teacher,HttpSession session){
		TbTeacher loginTeacer=(TbTeacher) session.getAttribute("teacher");
		if(loginTeacer!=null) {
			try {
				loginTeacer.setPassword(teacher.getPassword());
				teacherService.update(loginTeacer);
				return new Result(true, "修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "修改失败");
			}	
		}else {
			return new Result(false, "请先登录");
		}
		
	}	
	@RequestMapping("/myinfo")
	public Result myinfo(HttpSession session){
		TbTeacher loginTeacer=(TbTeacher) session.getAttribute("teacher");
		if(loginTeacer!=null) {
			try {
				return new Result(true, loginTeacer);
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false, "修改失败");
			}	
		}else {
			return new Result(false, "请先登录");
		}
		
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbTeacher findOne(Integer id){
		return teacherService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			teacherService.delete(ids);
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
	public PageResult search(@RequestBody TbTeacher teacher, int page, int rows  ){
		return teacherService.findPage(teacher, page, rows);		
	}
	@RequestMapping("/searchswork")
	public PageResult searchswork( HttpSession session,int page, int limit  ){
		TbTeacher teacher=(TbTeacher) session.getAttribute("teacher");
		teacher=new TbTeacher();
		teacher.setId(1);
		return sworkService.find(teacher.getId(), page, limit);
	}
	
}
