package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Raceinfo;

@Mapper
public interface RaceinfoDao {
    int insert(@Param("pojo") Raceinfo pojo);

    int insertSelective(@Param("pojo") Raceinfo pojo);

    int insertList(@Param("pojos") List<Raceinfo> pojo);

    int update(@Param("pojo") Raceinfo pojo);

    List<Raceinfo> findByStatusAndTerm(@Param("status")Integer status,@Param("term")Integer term);

    Raceinfo findFirstByUuid(@Param("uuid")String uuid);

    Raceinfo findById(@Param("id")Integer id);

    List<Raceinfo> findByPageNo(@Param("pageNo")Integer pageNo);


}
