package com.homework.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homework.mapper.TbAdminMapper;
import com.homework.pojo.TbAdmin;
import com.homework.pojo.TbAdminExample;
import com.homework.pojo.TbAdminExample.Criteria;
import com.homework.service.AdminService;
import com.homework.service.UserService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private TbAdminMapper adminMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbAdmin> findAll() {
		return adminMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbAdmin> page=   (Page<TbAdmin>) adminMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbAdmin user) {
		adminMapper.insert(user);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbAdmin user){
		adminMapper.updateByPrimaryKey(user);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbAdmin findOne(Integer id){
		return adminMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(Integer id) {
		adminMapper.deleteByPrimaryKey(id);
	}
	
	
		@Override
	public PageResult findPage(TbAdmin user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbAdminExample example=new TbAdminExample();
		Criteria criteria = example.createCriteria();
		
		if(user!=null){			
				
		}
		
		Page<TbAdmin> page= (Page<TbAdmin>)adminMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public TbAdmin login(TbAdmin user) {
			TbAdminExample example=new TbAdminExample();
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(user.getUsername());
			criteria.andPasswordEqualTo(user.getPassword());
			List<TbAdmin> list = adminMapper.selectByExample(example);
			if(list.size()>0)
				return list.get(0);
			return null;
		}
	
}
