package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Teacher;

@Mapper
public interface TeacherDao {
    int insert(@Param("pojo") Teacher pojo);

    int insertSelective(@Param("pojo") Teacher pojo);

    int insertList(@Param("pojos") List<Teacher> pojo);

    int update(@Param("pojo") Teacher pojo);

    List<Teacher> findTNameAndTUuid();


}
