package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Teamer;

@Mapper
public interface TeamerDao {
    int insert(@Param("pojo") Teamer pojo);

    int insertSelective(@Param("pojo") Teamer pojo);

    int insertList(@Param("pojos") List<Teamer> pojo);

    int update(@Param("pojo") Teamer pojo);

    Integer countByStuUuid(@Param("stuUuid")String stuUuid);

    List<String> findUuidByStuUuid(@Param("stuUuid")String stuUuid);

    List<Teamer> findByUuid(@Param("uuid")String uuid);

    List<Teamer> findByStuUuid(@Param("stuUuid")String stuUuid);

    Teamer findFirstByStatusAndStuUuidAndProUuid(@Param("status")Integer status,@Param("stuUuid")String stuUuid,@Param("proUuid")String proUuid);

    List<Teamer> findByStatusAndProUuid(@Param("status")Integer status,@Param("proUuid")String proUuid);

    Teamer findFirstByUuid(@Param("uuid")String uuid);

    List<Teamer> findByStatusAndStuUuid(@Param("status")Integer status,@Param("stuUuid")String stuUuid);

    List<String> findStunameByStatusAndProUuid(@Param("status")Integer status,@Param("proUuid")String proUuid);














}
