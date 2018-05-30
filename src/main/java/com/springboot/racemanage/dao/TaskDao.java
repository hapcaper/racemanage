package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.springboot.racemanage.po.Task;

@Mapper
public interface TaskDao {
    int insert(@Param("pojo") Task pojo);

    int insertSelective(@Param("pojo") Task pojo);

    int insertList(@Param("pojos") List<Task> pojo);

    int update(@Param("pojo") Task pojo);


    Integer countByStatusAndToUuid(@Param("status")Integer status,@Param("toUuid")String toUuid);

    List<Task> findByStatusAndToUuid(@Param("status")Integer status,@Param("toUuid")String toUuid);

    Integer countByStatusAndToUuidIn(@Param("status")Integer status,@Param("toUuidList")List<String> toUuidList);

    List<Task> findByProUuidAndStatusNot(@Param("proUuid")String proUuid,@Param("notStatus")Integer notStatus);

    List<Task> findByToUuidAndStatusOrStatus(@Param("toUuid")String toUuid,@Param("status1")Integer status1,@Param("status2")Integer status2);

    List<Task> findByToUuidAndStatusNot(@Param("toUuid")String toUuid,@Param("notStatus")Integer notStatus);

    Integer countByStatusAndToUuidAndProgress(@Param("status")Integer status,@Param("toUuid")String toUuid,@Param("progress")Integer progress);

    Integer countByStatusNotAndToUuidAndProgress(@Param("notStatus")Integer notStatus,@Param("toUuid")String toUuid,@Param("progress")Integer progress);


    Task findFirstByUuid(@Param("uuid")String uuid);

    Integer countByProgress(@Param("progress")Integer progress);

    Integer countByStatusAndProgressAndToUuid(@Param("status")Integer status,@Param("progress")Integer progress,@Param("toUuid")String toUuid);

    Integer countByStatusNotAndProgressAndToUuid(@Param("notStatus")Integer notStatus,@Param("progress")Integer progress,@Param("toUuid")String toUuid);













}
