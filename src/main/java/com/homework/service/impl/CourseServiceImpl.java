package com.homework.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.homework.mapper.TbCourseMapper;
import com.homework.pojo.TbCourse;
import com.homework.pojo.TbCourseExample;
import com.homework.pojo.TbCourseExample.Criteria;
import com.homework.service.CourseService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private TbCourseMapper courseMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbCourse> findAll(Integer tid) {
		TbCourseExample example=new TbCourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andTidEqualTo(tid);
		return courseMapper.selectByExample(example);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbCourse> page=   (Page<TbCourse>) courseMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbCourse course) {
		courseMapper.insert(course);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbCourse course){
		courseMapper.updateByPrimaryKey(course);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbCourse findOne(Integer id){
		return courseMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			courseMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbCourse course, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbCourseExample example=new TbCourseExample();
		Criteria criteria = example.createCriteria();
		
		if(course!=null){			
				
		}
		
		Page<TbCourse> page= (Page<TbCourse>)courseMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public PageResult searchcourse(Integer uid,int pageNum, int pageSize) {
			PageHelper.startPage(pageNum, pageSize);
			Page<TbCourse> page= (Page<TbCourse>)courseMapper.findAll(uid);		
			return new PageResult(0,"",page.getTotal(), page.getResult());
		}
	
}
