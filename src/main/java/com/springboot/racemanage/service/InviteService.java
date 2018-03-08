package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Invite;

import java.util.List;

public interface InviteService {
    int insert(Invite pojo);

    int insertSelective(Invite pojo);

    int insertList( List<Invite> pojo);

    int update( Invite pojo);

    Integer countByToUuid(String toUuid);

    Integer countByToUuidAndStatus(String toUuid,Integer status);

    List<Invite> findByToUuidAndStatus(String toUuid,Integer status);
}
