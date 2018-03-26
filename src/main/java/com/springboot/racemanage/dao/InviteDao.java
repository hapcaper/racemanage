package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Invite;

@Mapper
public interface InviteDao {
    int insert(@Param("pojo") Invite pojo);

    int insertSelective(@Param("pojo") Invite pojo);

    int insertList(@Param("pojos") List<Invite> pojo);

    int update(@Param("pojo") Invite pojo);

    Integer countByToUuid(@Param("toUuid")String toUuid);

    Integer countByToUuidAndStatus(@Param("toUuid")String toUuid,@Param("status")Integer status);

    List<Invite> findByToUuidAndStatus(@Param("toUuid")String toUuid,@Param("status")Integer status);

    Invite findByUuid(@Param("uuid")String uuid);







}
