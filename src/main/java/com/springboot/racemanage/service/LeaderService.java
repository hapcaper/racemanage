package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Leader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaderService {
    int insert(Leader pojo);

    int insertSelective(Leader pojo);

    int insertList(List<Leader> pojo);

    int update(Leader pojo);
}
