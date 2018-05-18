package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Project;

import java.util.List;

public interface ProjectService {
    int insert(Project pojo);

    int insertSelective(Project pojo);

    int insertList(List<Project> pojo);

    int update(Project pojo);

    List<Project> findByUuid(String uuid);

    Project findFirstByUuid(String uuid);

    List<Project> findByStatusAndUuidIn(Integer status,List<String> uuidList);

    List<Project> getProjectForRaceinfoDetail(String stuUuid,String raceinfoUuid);

    Integer countByStatus(Integer status);

    List<Project> findByStatus(Integer status);

    Project findById(Integer id);


}
