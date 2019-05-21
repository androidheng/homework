package com.homework.mapper;

import com.homework.pojo.TbSelect;
import com.homework.pojo.TbSelectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSelectMapper {
    int countByExample(TbSelectExample example);

    int deleteByExample(TbSelectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbSelect record);

    int insertSelective(TbSelect record);

    List<TbSelect> selectByExample(TbSelectExample example);

    TbSelect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbSelect record, @Param("example") TbSelectExample example);

    int updateByExample(@Param("record") TbSelect record, @Param("example") TbSelectExample example);

    int updateByPrimaryKeySelective(TbSelect record);

    int updateByPrimaryKey(TbSelect record);
}