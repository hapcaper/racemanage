package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Raceinfo;

import java.util.List;

public interface RaceinfoService {
    int insert(Raceinfo pojo);

    int insertSelective(Raceinfo pojo);

    int insertList(List<Raceinfo> pojo);

    int update(Raceinfo pojo);

    List<Raceinfo> findByStatusAndTerm(Integer status,Integer term);

}
