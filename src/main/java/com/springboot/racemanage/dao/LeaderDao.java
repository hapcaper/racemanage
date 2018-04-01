package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Leader;

@Mapper
public interface LeaderDao {
    int insert(@Param("pojo") Leader pojo);

    int insertSelective(@Param("pojo") Leader pojo);

    int insertList(@Param("pojos") List<Leader> pojo);

    int update(@Param("pojo") Leader pojo);

    Leader findByLStatusAndLPasswordAndLName(@Param("lStatus")Integer lStatus,@Param("lPassword")String lPassword,@Param("lName")String lName);

    Leader findByLStatusAndLPasswordAndLNumber(@Param("lStatus")Integer lStatus,@Param("lPassword")String lPassword,@Param("lNumber")String lNumber);


}
