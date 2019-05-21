package com.homework.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homework.mapper.TbTeacherMapper;
import com.homework.pojo.TbTeacher;
import com.homework.pojo.TbTeacherExample;
import com.homework.pojo.TbTeacherExample.Criteria;
import com.homework.service.TeacherService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TbTeacherMapper teacherMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbTeacher> findAll() {
		return teacherMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbTeacher> page=   (Page<TbTeacher>) teacherMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbTeacher teacher) {
		teacherMapper.insert(teacher);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbTeacher teacher){
		teacherMapper.updateByPrimaryKeySelective(teacher);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbTeacher findOne(Integer id){
		return teacherMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			teacherMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbTeacher teacher, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbTeacherExample example=new TbTeacherExample();
		Criteria criteria = example.createCriteria();
		
		if(teacher!=null){			
				
		}
		
		Page<TbTeacher> page= (Page<TbTeacher>)teacherMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public TbTeacher login(TbTeacher teacher) {
			TbTeacherExample example=new TbTeacherExample();
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(teacher.getUsername());
			criteria.andPasswordEqualTo(teacher.getPassword());
			List<TbTeacher> list = teacherMapper.selectByExample(example);
			if(list.size()>0)
				return list.get(0);
			return null;
		}
	
}
