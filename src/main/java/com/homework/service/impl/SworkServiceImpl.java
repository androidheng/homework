package com.homework.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homework.mapper.TbSworkMapper;
import com.homework.pojo.TbSelect;
import com.homework.pojo.TbSwork;
import com.homework.pojo.TbSworkExample;
import com.homework.pojo.TbSworkExample.Criteria;
import com.homework.service.SworkService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SworkServiceImpl implements SworkService {

	@Autowired
	private TbSworkMapper sworkMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSwork> findAll() {
		return sworkMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSwork> page=   (Page<TbSwork>) sworkMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbSwork swork) {
		sworkMapper.insert(swork);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbSwork swork){
		sworkMapper.updateByPrimaryKeySelective(swork);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbSwork findOne(Integer id){
		return sworkMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			sworkMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSwork swork, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSworkExample example=new TbSworkExample();
		Criteria criteria = example.createCriteria();
		
		if(swork!=null){			
				
		}
		
		Page<TbSwork> page= (Page<TbSwork>)sworkMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public TbSwork findTtid(Integer id, Integer ttid) {
			TbSworkExample example=new TbSworkExample();
			Criteria criteria = example.createCriteria();
			criteria.andTtidEqualTo(ttid);
			criteria.andUidEqualTo(id);
			List<TbSwork> list = sworkMapper.selectByExample(example);
			if(list.size()>0)
				return list.get(0);
			return null;
		}

		@Override
		public PageResult find(Integer id, int pageNum, int pageSize) {
			
			PageHelper.startPage(pageNum, pageSize);
			
			Page<TbSwork> page= (Page<TbSwork>)sworkMapper.find(id);		
			return new PageResult(page.getTotal(), page.getResult());
		}


	
	
}
