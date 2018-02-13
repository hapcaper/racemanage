package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Project;

@Mapper
public interface ProjectDao {
    int insert(@Param("pojo") Project pojo);

    int insertSelective(@Param("pojo") Project pojo);

    int insertList(@Param("pojos") List<Project> pojo);

    int update(@Param("pojo") Project pojo);
}
