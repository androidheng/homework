package com.homework.service;
import java.util.List;

import com.homework.pojo.TbSelect;
import com.homework.pojo.TbSwork;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SworkService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbSwork> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbSwork swork);
	
	
	/**
	 * 修改
	 */
	public void update(TbSwork swork);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbSwork findOne(Integer id);
	
	
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
	public PageResult findPage(TbSwork swork, int pageNum,int pageSize);


	public TbSwork findTtid(Integer id, Integer ttid);


	public PageResult find(Integer id, int page, int limit);




	
	
}
