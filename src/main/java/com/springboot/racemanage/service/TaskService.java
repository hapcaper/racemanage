package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskService {
    int insert(Task pojo);

    int insertSelective(Task pojo);

    int insertList(List<Task> pojo);

    int update(Task pojo);
}
