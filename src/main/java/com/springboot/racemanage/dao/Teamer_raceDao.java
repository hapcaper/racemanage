package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Teamer_race;

@Mapper
public interface Teamer_raceDao {
    int insert(@Param("pojo") Teamer_race pojo);

    int insertSelective(@Param("pojo") Teamer_race pojo);

    int insertList(@Param("pojos") List<Teamer_race> pojo);

    int update(@Param("pojo") Teamer_race pojo);
}
