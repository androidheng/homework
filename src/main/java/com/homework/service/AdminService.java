package com.homework.service;
import java.util.List;
import com.homework.pojo.TbAdmin;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface AdminService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbAdmin> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbAdmin user);
	
	
	/**
	 * 修改
	 */
	public void update(TbAdmin user);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbAdmin findOne(Integer id);
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer  id);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbAdmin user, int pageNum,int pageSize);


	public TbAdmin login(TbAdmin user);
	
}
