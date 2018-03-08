package com.springboot.racemanage.controller;


import org.apache.catalina.Server;
import org.apache.catalina.core.StandardServer;
import org.junit.Test;
import org.springframework.stereotype.Controller;

public class Ss {
    @Test
    public void rrr() {
        Server server = new StandardServer();
        server.setAddress("123456");
        System.out.println(server.getAddress());



    }
}
