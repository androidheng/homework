package com.homework.service;
import java.util.List;
import com.homework.pojo.TbCourse;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CourseService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbCourse> findAll(Integer tid);
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbCourse course);
	
	
	/**
	 * 修改
	 */
	public void update(TbCourse course);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbCourse findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbCourse course, int pageNum,int pageSize);


	public PageResult searchcourse(Integer uid,int page, int limit);
	
}
