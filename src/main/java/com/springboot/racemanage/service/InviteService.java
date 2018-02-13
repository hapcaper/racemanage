package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Invite;

import java.util.List;

public interface InviteService {
    int insert(Invite pojo);

    int insertSelective(Invite pojo);

    int insertList( List<Invite> pojo);

    int update( Invite pojo);
}
