package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Solution;

@Mapper
public interface SolutionDao {
    int insert(@Param("pojo") Solution pojo);

    int insertSelective(@Param("pojo") Solution pojo);

    int insertList(@Param("pojos") List<Solution> pojo);

    int update(@Param("pojo") Solution pojo);

    Solution findByUuid(@Param("uuid")String uuid);

    Solution findByResultAndStatusAndUuid(@Param("result")Integer result,@Param("status")Integer status,@Param("uuid")String uuid);

    Solution findByResultAndStatusAndTaskUuid(@Param("result")Integer result,@Param("status")Integer status,@Param("taskUuid")String taskUuid);


}
