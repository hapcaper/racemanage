package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Project;

import java.util.List;

public interface ProjectService {
    int insert(Project pojo);

    int insertSelective(Project pojo);

    int insertList(List<Project> pojo);

    int update(Project pojo);
}
