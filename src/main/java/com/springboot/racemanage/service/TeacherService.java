package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Teacher;

import java.util.List;

public interface TeacherService {

    int insert( Teacher pojo);

    int insertSelective(Teacher pojo);

    int insertList(List<Teacher> pojo);

    int update( Teacher pojo);

    List<Teacher> findTNameAndTUuid();

    Integer countByTStatus(Integer tStatus);

    List<Teacher> findByTStatus(Integer tStatus);

}
