package com.springboot.racemanage.dao;

import com.springboot.racemanage.po.Race;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RaceDao {
    int insert(@Param("pojo") Race pojo);

    int insertSelective(@Param("pojo") Race pojo);

    int insertList(@Param("pojos") List<Race> pojo);

    int update(@Param("pojo") Race pojo);

    List<Race> getStuRaceListByTerm(@Param("stuUuid")String stuUuid,@Param("term")Integer term);

    Race findByUuid(@Param("uuid")String uuid);

    List<Race> getByStuUuidAndStatusAndResultNot(@Param("stuUuid")String stuUuid,@Param("status")Integer status,@Param("resultnot")Integer resultnot);

    List<Race> findByStatusAndTerm(@Param("status")Integer status,@Param("term")Integer term);

    List<Race> findByStatusAndTermAndTUuid(@Param("status")Integer status,@Param("term")Integer term,@Param("tUuid")String tUuid);

    List<Race> findByStatusAndTUuidAndProgress(@Param("status")Integer status,@Param("tUuid")String tUuid,@Param("progress")Integer progress);



}
