package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {

    int insert( Teacher pojo);

    int insertSelective(Teacher pojo);

    int insertList(List<Teacher> pojo);

    int update( Teacher pojo);
}
