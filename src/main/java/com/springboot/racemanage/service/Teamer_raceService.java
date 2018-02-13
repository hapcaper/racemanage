package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Teamer_race;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Teamer_raceService {
    int insert(Teamer_race pojo);

    int insertSelective(Teamer_race pojo);

    int insertList(List<Teamer_race> pojo);

    int update(Teamer_race pojo);
}
