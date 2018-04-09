package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Student;

import java.util.List;

public interface StudentService {
    int insert(Student pojo);
    int insertSelective(Student pojo);
    int insertList(List<Student> pojos);
    int update(Student pojo);

    Student findFirstByStuNumberAndStuPasswordAndStuStatus(String stunumber,String password,Integer status);

    List<Student> findStuUuidAndStuName();

    List<Student> findStuUuidAndStuNameByStuUuidNot(String notStuUuid);

    Integer countByStuStatus(Integer stuStatus);


}
