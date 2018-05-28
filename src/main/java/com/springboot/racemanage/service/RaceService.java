package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Race;

import java.util.List;

public interface RaceService {
    int insert(Race pojo);

    int insertSelective(Race pojo);

    int insertList(List<Race> pojo);

    int update(Race pojo);

    List<Race> getStuRaceListByTerm(String stuUuid,Integer term);

    Race findByUuid(String uuid);

    List<Race> getAchivementListByStuUuid(String stuUuid);

    List<Race> findByStatusAndTerm(Integer status,Integer term);

    List<Race> findByStatusAndTermAndTUuid(Integer status,Integer term,String tUuid);

    List<Race> findByStatusAndTUuidAndProgress(Integer status,String tUuid,Integer progress);

    List<Race> findByPageNo(Integer pageNo);

}
