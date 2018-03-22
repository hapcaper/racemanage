package com.springboot.racemanage.controller;

import com.springboot.racemanage.RacemanageApplication;
import com.springboot.racemanage.dao.MessageDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;


@SpringBootTest(classes = RacemanageApplication.class)
@MybatisTest
public class StudentControllerTest {

    @Autowired
    private MessageDao messageDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void msgCenter() {
//        Map<String ,String > map = messageDao.getMsgWithStuName("951753");
//        System.out.println(map.size());
    }
}