package com.homework.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.homework.pojo.TbTeacher;
import com.homework.pojo.TbTwork;
import com.homework.service.TworkService;
import com.homework.util.DateUtils;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/twork")
public class TworkController {

	@Autowired
	private TworkService tworkService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbTwork> findAll(){			
		return tworkService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return tworkService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param twork
	 * @return
	 */
	@RequestMapping("/addOrUpdate")
	public Result addOrUpdate(@RequestBody  TbTwork twork,HttpSession session){
			TbTeacher tbTeacher=(TbTeacher) session.getAttribute("teacher");
			if(tbTeacher!=null) {
				
			
			if(StringUtils.isEmpty(twork.getId())) {//没有id 添加操作
				twork.setTid(tbTeacher.getId());
				twork.setEndtime(twork.getEndtime()+" 23:59:59");
				tworkService.add(twork);
				return new Result(true, "添加成功");
			}else {
				try {
					tworkService.update(twork);
					return new Result(true, "修改成功");
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(false, "修改失败");
				}	
			}
			
			}else {
				return new Result(false, "请先登录");
			}
			
	}
	
	/**
	 * 修改
	 * @param twork
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbTwork twork){
		try {
			tworkService.update(twork);
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
	public TbTwork findOne(Integer id){
		return tworkService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(@RequestBody TbTwork twork){
		try {
			tworkService.delete(twork.getId());
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
	public PageResult search(String key, int page, int limit,HttpSession session  ){
		TbTeacher tbTeacher=(TbTeacher) session.getAttribute("teacher");
		TbTwork twork=new TbTwork();
		twork.setTid(tbTeacher.getId());
		return tworkService.findPage(twork, page, limit);		
	}
	
}
