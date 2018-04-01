package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Leader;

import java.util.List;

public interface LeaderService {
    int insert(Leader pojo);

    int insertSelective(Leader pojo);

    int insertList(List<Leader> pojo);

    int update(Leader pojo);

    Leader findByLStatusAndLPasswordAndLName(Integer lStatus,String lPassword,String lName);

    Leader findByLStatusAndLPasswordAndLNumber(Integer lStatus,String lPassword,String lNumber);

}
