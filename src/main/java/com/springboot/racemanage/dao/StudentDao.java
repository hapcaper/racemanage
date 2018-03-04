
package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Student;

@Mapper
public interface StudentDao {
    int insert(@Param("pojo") Student pojo);

    int insertSelective(@Param("pojo") Student pojo);

    int insertList(@Param("pojos") List<Student> pojo);

    int update(@Param("pojo") Student pojo);

    Student findFirstByStuNumberAndStuPasswordAndStuStatus(@Param("stuNumber")String stuNumber,@Param("stuPassword")String stuPassword,@Param("stuStatus")Integer stuStatus);

}
