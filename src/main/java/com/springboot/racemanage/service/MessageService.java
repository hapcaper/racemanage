package com.springboot.racemanage.service;

import com.springboot.racemanage.po.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    int insert( Message pojo);

    int insertSelective(Message pojo);

    int insertList(List<Message> pojo);

    int update(Message pojo);

    Integer countByToUuidAndStatus(String toUuid,Integer status);

    List<Message> findByToUuidAndStatus(String toUuid,Integer status);

    List<Map<String, String>> getMsgWithStuName( String toUuid);

}
