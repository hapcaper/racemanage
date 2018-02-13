package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Extend;

import java.util.List;

public interface ExtendService {
    int insert(Extend pojo);

    int insertSelective(Extend pojo);

    int insertList(List<Extend> pojo);

    int update(Extend pojo);
}
