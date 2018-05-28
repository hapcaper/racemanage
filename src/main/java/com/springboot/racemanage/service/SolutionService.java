package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Solution;

import java.util.List;

public interface SolutionService {
    int insert(Solution pojo);

    int insertSelective(Solution pojo);

    int insertList(List<Solution> pojo);

    int update(Solution pojo);

    Solution findByUuid(String uuid);

    Solution findByResultAndStatusAndUuid(Integer result,Integer status,String uuid);

    Solution findByResultAndStatusAndTaskUuid(Integer result,Integer status,String taskUuid);

}
