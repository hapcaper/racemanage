package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Extend;

@Mapper
public interface ExtendDao {
    int insert(@Param("pojo") Extend pojo);

    int insertSelective(@Param("pojo") Extend pojo);

    int insertList(@Param("pojos") List<Extend> pojo);

    int update(@Param("pojo") Extend pojo);
}
