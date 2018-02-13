package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Race;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RaceService {
    int insert(Race pojo);

    int insertSelective(Race pojo);

    int insertList(List<Race> pojo);

    int update(Race pojo);
}
