package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Teamer;

import java.util.List;

public interface TeamerService {
    int insert(Teamer pojo);

    int insertSelective(Teamer pojo);

    int insertList(List<Teamer> pojo);

    int update(Teamer pojo);

    Integer countByStuUuid(String stuUuid);

    List<String> findUuidByStuUuid(String stuUuid);

    List<Teamer> findByUuid(String uuid);

    List<Teamer> findByStuUuid(String stuUuid);

    Teamer findFirstByStatusAndStuUuidAndProUuid(Integer status,String stuUuid,String proUuid);

    List<Teamer> findByStatusAndProUuid(Integer status,String proUuid);

    Teamer findFirstByUuid(String uuid);

    List<Teamer> findByStatusAndStuUuid(Integer status,String stuUuid);

    List<String> findStunameByStatusAndProUuid(Integer status,String proUuid);

}
