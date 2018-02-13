package com.springboot.racemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.springboot.racemanage.po.Term;

@Mapper
public interface TermDao {
    int insert(@Param("pojo") Term pojo);

    int insertSelective(@Param("pojo") Term pojo);

    int insertList(@Param("pojos") List<Term> pojo);

    int update(@Param("pojo") Term pojo);
}
