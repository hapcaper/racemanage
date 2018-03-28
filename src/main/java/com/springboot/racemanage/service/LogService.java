package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Log;

import java.util.List;
import java.util.Map;

public interface LogService {
    int insert(Log pojo);

    int insertSelective(Log pojo);

    int insertList(List<Log> pojo);

    int update(Log pojo);

    List<Log> findByStatusAndProUuid(Integer status,String proUuid);

    List<Map> getLogAndTeamerNameByProUuid(String proUuid);
}
