package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Term;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermService {
    int insert(Term pojo);

    int insertSelective(Term pojo);

    int insertList(List<Term> pojo);

    int update(Term pojo);
}
