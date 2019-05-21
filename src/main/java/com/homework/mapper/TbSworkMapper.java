package com.homework.mapper;

import com.github.pagehelper.Page;
import com.homework.pojo.TbSwork;
import com.homework.pojo.TbSworkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSworkMapper {
    int countByExample(TbSworkExample example);

    int deleteByExample(TbSworkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSwork record);

    int insertSelective(TbSwork record);

    List<TbSwork> selectByExample(TbSworkExample example);

    TbSwork selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSwork record, @Param("example") TbSworkExample example);

    int updateByExample(@Param("record") TbSwork record, @Param("example") TbSworkExample example);

    int updateByPrimaryKeySelective(TbSwork record);

    int updateByPrimaryKey(TbSwork record);

	List<TbSwork> find(Integer id);
}