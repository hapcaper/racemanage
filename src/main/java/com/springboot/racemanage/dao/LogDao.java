package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Log;

@Mapper
public interface LogDao {
    int insert(@Param("pojo") Log pojo);

    int insertSelective(@Param("pojo") Log pojo);

    int insertList(@Param("pojos") List<Log> pojo);

    int update(@Param("pojo") Log pojo);
}
