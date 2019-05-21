package com.homework.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.homework.pojo.TbSelect;
import com.homework.pojo.TbUser;
import com.homework.service.SelectService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/select")
public class SelectController {

	@Autowired
	private SelectService selectService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSelect> findAll(){			
		return selectService.findAll(1);
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return selectService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param select
	 * @return
	 */
	@RequestMapping(value="/add",produces = "application/json;charset=UTF-8")
	public Result add(@RequestBody TbSelect select,HttpSession session){
//		TbUser user=(TbUser) session.getAttribute("student");
		TbUser user=new TbUser();
		user.setId(1);
		try {
			select.setUid(user.getId());
			selectService.add(select);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param select
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbSelect select){
		try {
			selectService.update(select);
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
	public TbSelect findOne(Integer id){
		return selectService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			selectService.delete(ids);
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
	public PageResult search(@RequestBody TbSelect select, int page, int rows  ){
		return selectService.findPage(select, page, rows);		
	}
	
}
