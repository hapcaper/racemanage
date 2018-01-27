package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Student;

import java.util.List;

public interface StudentService {
    int insert(Student pojo);
    int insertSelective(Student pojo);
    int insertList(List<Student> pojos);
    int update(Student pojo);

}
