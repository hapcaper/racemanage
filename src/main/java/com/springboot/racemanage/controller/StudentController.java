package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.Student;
import com.springboot.racemanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(HttpSession httpSession,Model model, @RequestParam("stunumber") String stunumber, @RequestParam("password") String password){
        Student student = studentService.findFirstByStuNumberAndStuPasswordAndStuStatus(stunumber, password, 1);
        if (student!=null){
            httpSession.setAttribute("student",student);
            return "student/index";
        }else {
            model.addAttribute("errorMsg", "学号或密码错误!!");
            return "login";
        }

    }

    @RequestMapping("/toInsertStudent.do")
    public String toInsertStudent(Model model) {

        return "student/toInsertStudent";
    }

    @RequestMapping("/insertStudent.do")
    public String insertStudent(Model model) {
        Student student = new Student();
        student.setStuNumber("201470024136");
        int a = studentService.insertSelective(student);
        System.out.println(a+"'''''''''''''");
        return "fuck";
    }
    @RequestMapping("/toBlankPage.do")
    public String toBlankPage(Model model) {
        return "student/blank";
    }

}
