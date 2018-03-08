package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    int insert(Task pojo);

    int insertSelective(Task pojo);

    int insertList(List<Task> pojo);

    int update(Task pojo);


    Integer countByStatusAndToUuid(Integer status,String toUuid);

    Integer countByStatusAndToUuidIn(Integer status,List<String> toUuidList);

    List<Task> findByStatusAndToUuid(Integer status,String toUuid);
}
