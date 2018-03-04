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

    Integer countByTo(@Param("to")String to);

    Integer countByToAndStatus(@Param("to")String to,@Param("status")Integer status);



}
