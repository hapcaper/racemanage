package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.springboot.racemanage.po.Task;

@Mapper
public interface TaskDao {
    int insert(@Param("pojo") Task pojo);

    int insertSelective(@Param("pojo") Task pojo);

    int insertList(@Param("pojos") List<Task> pojo);

    int update(@Param("pojo") Task pojo);


    Integer countByStatusAndToUuid(@Param("status")Integer status,@Param("toUuid")String toUuid);

    List<Task> findByStatusAndToUuid(@Param("status")Integer status,@Param("toUuid")String toUuid);



    Integer countByStatusAndToUuidIn(@Param("status")Integer status,@Param("toUuidList")List<String> toUuidList);





}
