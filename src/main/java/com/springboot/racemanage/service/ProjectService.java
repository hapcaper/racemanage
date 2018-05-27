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

    List<Project> findByStatusAndTUuid(Integer status,String tUuid);


    /**
     * 教师查询自己的可以参加比赛的项目
     * @param tUUID 教师的tUUID
     * @param term 当前期
     * @param raceInfoUUID 要报名的赛事UUID
     * @return 教师可以报名该赛事的项目列表
     */
    List<Project> findCanRaceProject(String tUUID, Integer term, String raceInfoUUID);

}
