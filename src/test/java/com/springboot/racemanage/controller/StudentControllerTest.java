package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.Student;
import com.springboot.racemanage.service.StudentService;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class StudentControllerTest {
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    private Configuration configuration = new Configuration();
    private ApplicationContext applicationContext;
    @Before
    public void setUp() throws Exception {
        applicationContext = new ApplicationContext(new StandardContext());

        System.out.println(applicationContext.getAttributeNames());
        System.out.println(configuration);

        sqlSessionFactoryBuilder.build(configuration);
        System.out.println("||||||||||||");
    }

    @After
    public void tearDown() throws Exception {

        System.out.println("8888888888888");
    }

    @Test
    public void login() {



    }

    @Test
    public void profile() {
    }
}