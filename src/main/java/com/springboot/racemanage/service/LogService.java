package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogService {
    int insert(Log pojo);

    int insertSelective(Log pojo);

    int insertList(List<Log> pojo);

    int update(Log pojo);
}
