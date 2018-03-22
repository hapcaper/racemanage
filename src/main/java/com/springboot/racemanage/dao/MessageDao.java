package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.springboot.racemanage.po.Message;

@Mapper
public interface MessageDao {
    int insert(@Param("pojo") Message pojo);

    int insertSelective(@Param("pojo") Message pojo);

    int insertList(@Param("pojos") List<Message> pojo);

    int update(@Param("pojo") Message pojo);

    Integer countByToUuidAndStatus(@Param("toUuid")String toUuid,@Param("status")Integer status);

    List<Message> findByToUuidAndStatus(@Param("toUuid")String toUuid,@Param("status")Integer status);

    List<Map<String, String>> getMsgWithStuName(@Param("toUuid") String toUuid);





}
