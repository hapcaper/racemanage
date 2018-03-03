package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.Student;
import com.springboot.racemanage.service.InviteService;
import com.springboot.racemanage.service.MessageService;
import com.springboot.racemanage.service.StudentService;
import com.springboot.racemanage.service.TeamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.lang.Nullable;
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

    @Autowired
    TeamerService teamerService;

    @Autowired
    InviteService inviteService;

    @Autowired
    MessageService messageService;

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

    @RequestMapping("/profile.do")
    public String profile(Model model , HttpSession httpSession) {
        Student student = (Student) httpSession.getAttribute("student");
        Integer proNum = teamerService.countByStuUuid(student.getStuUuid());
        Integer inviteNum = inviteService.countByToAndStatus(student.getStuUuid(),1);
        Integer msgNum = messageService.countByToAndStatus(student.getStuUuid(), 1);
        model.addAttribute("inviteNum", inviteNum).addAttribute("msgNum", msgNum);
        model.addAttribute("proNum",proNum);
        model.addAttribute("student", student);
        return "student/profile";
    }

    @RequestMapping(value = "/updateProfile.do",method = RequestMethod.POST)
    public String updateProfile(Model model , HttpSession httpSession,
                                @RequestParam("email")String email,
                                @RequestParam("phone")String phone,
                                @RequestParam("oldPasswd")String oldPasswd,
                                @RequestParam("newPasswd")String newPasswd,
                                @RequestParam("photo")String photo) {
        model.addAttribute("menuSelected1", "");
        Student student = (Student) httpSession.getAttribute("student");
        if (phone.length()!=0) {
            student.setPhoto(photo);
        }
        if (email.length()!=0) {
            student.setStuEmail(email);
        }

        String passwdMsg = null;
        if (newPasswd.length() >= 6) {
            student.setStuPassword(newPasswd);
        } else {
            passwdMsg = "密码长度必须不小于6位";
        }
        if (phone.length()!=0) {
            student.setStuPhone(phone);
        }
        int a = studentService.update(student);
        String updateMsg = null;
        if (a != 0) {
            updateMsg = "更新成功";
        } else {
            updateMsg = "更新失败";
        }
        Integer proNum = teamerService.countByStuUuid(student.getStuUuid());
        Integer inviteNum = inviteService.countByToAndStatus(student.getStuUuid(),1);
        Integer msgNum = messageService.countByToAndStatus(student.getStuUuid(), 1);
        model.addAttribute("inviteNum", inviteNum).addAttribute("msgNum", msgNum);
        model.addAttribute("proNum",proNum);
        model.addAttribute("student", student);
        model.addAttribute("updateMsg",updateMsg);
        model.addAttribute("passwdMsg", passwdMsg);

        System.out.println(student+"--------------------");

        return "student/profile";

    }

}
