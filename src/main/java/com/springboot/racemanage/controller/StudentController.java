package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.Project;
import com.springboot.racemanage.po.Student;
import com.springboot.racemanage.po.Teacher;
import com.springboot.racemanage.po.Teamer;
import com.springboot.racemanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

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

    @Autowired
    TeacherService teacherService;

    @Autowired
    RaceService raceService;

    @Autowired
    RaceinfoService raceinfoService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(HttpSession httpSession,Model model, @RequestParam("stunumber") String stunumber, @RequestParam("password") String password){
        model.addAttribute("menuSelected1", "index");
        Student student = studentService.findFirstByStuNumberAndStuPasswordAndStuStatus(stunumber, password, 1);
        List<Teamer> teamerList = teamerService.findByStuUuid(student.getStuUuid());
        if (student != null)
            if (teamerList != null) {
                httpSession.setAttribute("student", student);
                httpSession.setAttribute("teamerList", teamerList);
                return "student/index";
            } else {
                model.addAttribute("errorMsg", "学号或密码错误!!");
                return "login";
            }
        else {
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

    @RequestMapping("/profile.do")
    public String profile(Model model , HttpSession httpSession) {
        model.addAttribute("menuSelected1", "profile");
        Student student = (Student) httpSession.getAttribute("student");
        Integer proNum = teamerService.countByStuUuid(student.getStuUuid());
        Integer inviteNum = inviteService.countByToUuidAndStatus(student.getStuUuid(),1);
        Integer msgNum = messageService.countByToUuidAndStatus(student.getStuUuid(), 1);
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
        model.addAttribute("menuSelected1", "profile");
        Student student = (Student) httpSession.getAttribute("student");
        if (phone.length()!=0) {
            student.setPhoto(photo);
        }
        if (email.length()!=0) {
            student.setStuEmail(email);
        }

        String passwdMsg = "OK";
        if (newPasswd.length() >= 6) {
            student.setStuPassword(newPasswd);
        } else {
            passwdMsg = "密码长度必须不小于6位";
        }
        if (phone.length()!=0) {
            student.setStuPhone(phone);
        }
        int a = studentService.update(student);
        String updateMsg = "更新成功";
        if (a == 0) {
            updateMsg = "更新失败";
        }
        Integer proNum = teamerService.countByStuUuid(student.getStuUuid());
        Integer inviteNum = inviteService.countByToUuidAndStatus(student.getStuUuid(),1);
        Integer msgNum = messageService.countByToUuidAndStatus(student.getStuUuid(), 1);
        model.addAttribute("inviteNum", inviteNum).addAttribute("msgNum", msgNum);
        model.addAttribute("proNum",proNum);
        model.addAttribute("student", student);
        model.addAttribute("updateMsg",updateMsg);
        model.addAttribute("passwdMsg", passwdMsg);

        System.out.println(student+"--------------------");

        return "student/profile";

    }

    @RequestMapping("/toAddProject.do")
    public String toAddProject(Model model,HttpSession httpSession) {
        model.addAttribute("menuSelected1", "addProject");
        Student student = (Student) httpSession.getAttribute("student");
        List<Student> stuList = studentService.findStuUuidAndStuNameByStuUuidNot(student.getStuUuid());
        System.out.println(stuList);
        for (Student stu : stuList) {
            System.out.println(stu);
        }
        List<Teacher> teList = teacherService.findTNameAndTUuid();
        System.out.println(teList);
        model.addAttribute("stuList", stuList);
        model.addAttribute("teList",teList);
        return "student/addProject";
    }

    @RequestMapping(value = "/addProject.do",method = RequestMethod.POST)
    public String addProject(Model model,HttpSession httpSession,
                             @RequestParam("proName")String proName,
                             @RequestParam("proDescription")String proDescription,
                             @RequestParam("document")MultipartFile document,
                             @RequestParam("stuUuid[]")List<String> stuUuids,
                             @RequestParam("teUuid")String teUuid) {
        Student student = (Student) httpSession.getAttribute("student");
        Project project = new Project();
        project.setUuid(UUID.randomUUID().toString());
        project.setName(proName);
        project.setDescription(proDescription);
        project.settUuid(teUuid);
        project.setHeadman(student.getStuUuid());
        try {
            OutputStream outputStream = new FileOutputStream("");
            outputStream.write(document.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/student/projectList.do";
    }

    @RequestMapping("/projectList.do")
    public String projectList(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "projectList");
        List<Teamer> teamerList = (List<Teamer>) httpSession.getAttribute("teamerList");
        System.out.println(teamerList+"///////////////");
        List<Project> projectList = new ArrayList<>();
        for (Teamer t:teamerList) {
            projectList.add(projectService.findFirstByUuid(t.getProUuid()));
        }
        model.addAttribute("projectList", projectList);
        return "student/projectList";
    }
}
