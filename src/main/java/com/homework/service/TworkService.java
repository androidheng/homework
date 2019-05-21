package com.homework.service;
import java.util.List;

import com.homework.pojo.TbSelect;
import com.homework.pojo.TbTwork;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface TworkService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbTwork> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbTwork twork);
	
	
	/**
	 * 修改
	 */
	public void update(TbTwork twork);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbTwork findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer  id);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbTwork twork, int pageNum,int pageSize);


	public PageResult find(List<TbSelect> list, int pageNum, int pageSize);
	
}
