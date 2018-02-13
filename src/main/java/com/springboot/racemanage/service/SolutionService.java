package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Solution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SolutionService {
    int insert(Solution pojo);

    int insertSelective(Solution pojo);

    int insertList(List<Solution> pojo);

    int update(Solution pojo);
}
