package com.springboot.racemanage.dao;

import com.springboot.racemanage.po.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Race;

@Mapper
public interface RaceDao {
    int insert(@Param("pojo") Race pojo);

    int insertSelective(@Param("pojo") Race pojo);

    int insertList(@Param("pojos") List<Race> pojo);

    int update(@Param("pojo") Race pojo);





}
