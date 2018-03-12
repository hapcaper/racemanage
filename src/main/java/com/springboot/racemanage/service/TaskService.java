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

    List<Task> findByProUuidAndStatusNot(String proUuid,Integer notStatus);

    List<Task> findByToUuidAndStatusOrStatus(String toUuid,Integer status1,Integer status2);

    List<Task> findByToUuidAndStatusNot(String toUuid,Integer notStatus);

    Integer countByStatusAndToUuidAndProgress(Integer status,String toUuid,Integer progress);

    Integer countByStatusNotAndToUuidAndProgress(Integer notStatus,String toUuid,Integer progress);


    Task findFirstByUuid(String uuid);






}
