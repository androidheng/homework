package com.homework.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homework.mapper.TbSelectMapper;
import com.homework.pojo.TbSelect;
import com.homework.pojo.TbSelectExample;
import com.homework.pojo.TbSelectExample.Criteria;
import com.homework.service.SelectService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SelectServiceImpl implements SelectService {

	@Autowired
	private TbSelectMapper selectMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSelect> findAll(Integer id) {
		TbSelectExample example=new TbSelectExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(id);
		return selectMapper.selectByExample(example);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSelect> page=   (Page<TbSelect>) selectMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbSelect select) {
		selectMapper.insert(select);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbSelect select){
		selectMapper.updateByPrimaryKey(select);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbSelect findOne(Integer id){
		return selectMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			selectMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSelect select, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSelectExample example=new TbSelectExample();
		Criteria criteria = example.createCriteria();
		
		if(select!=null){			
				
		}
		
		Page<TbSelect> page= (Page<TbSelect>)selectMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
