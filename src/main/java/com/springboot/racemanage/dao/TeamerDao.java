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


    


}
