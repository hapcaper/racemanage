package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Term;

import java.util.List;

public interface TermService {
    int insert(Term pojo);

    int insertSelective(Term pojo);

    int insertList(List<Term> pojo);

    int update(Term pojo);

    Term findFirstByStatusOrderByTerm(Integer status);
}
