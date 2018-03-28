package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.springboot.racemanage.po.Log;

@Mapper
public interface LogDao {
    int insert(@Param("pojo") Log pojo);

    int insertSelective(@Param("pojo") Log pojo);

    int insertList(@Param("pojos") List<Log> pojo);

    int update(@Param("pojo") Log pojo);

    List<Log> findByProUuid(@Param("proUuid")String proUuid);

    List<Log> findByStatusAndProUuid(@Param("status")Integer status,@Param("proUuid")String proUuid);

    List<Map> getLogAndTeamerNameByProUuid(@Param("proUuid")String proUuid);



}
