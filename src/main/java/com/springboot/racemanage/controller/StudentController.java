package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.Student;
import com.springboot.racemanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/toInsertStudent")
    public String toInsertStudent(Model model) {

        return "student/toInsertStudent";
    }

    @RequestMapping("/insertStudent")
    public String insertStudent(Model model) {
        Student student = new Student();
        student.setAccount("232");
        student.setUuid(UUID.randomUUID().toString());
        student.setName("李自豪");
        student.setGender("男");
        student.setStatus(1);
        student.setStuNumber("201470024136");
        int a = studentService.insert(student);
        System.out.println(a+"'''''''''''''");
        return "fuck";
    }
}
