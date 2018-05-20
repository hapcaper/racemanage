package com.springboot.racemanage.controller;


import com.springboot.racemanage.po.Teacher;
import com.springboot.racemanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
@Transactional
public class TeacherController {

    @Autowired
    TermService termService;

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

    @Autowired
    TaskService taskService;

    @Autowired
    SolutionService solutionService;

    @Autowired
    LogService logService;

    @RequestMapping("/login.do")
    public String login(Model model, HttpSession httpSession,
                        @RequestParam("tNumber") String tNumber,
                        @RequestParam("password") String password) {
        Teacher teacher = teacherService.login(1, tNumber, password);

        if (teacher == null) {
            model.addAttribute("errMsg", "工号或密码错误");
            return "login";
        } else {
            httpSession.setAttribute("teacher", teacher);
            return "forward:/teacher/index.do";
        }
    }

    @RequestMapping("/index.do")
    public String index(Model model, HttpSession httpSession) {
        //TODO  首页待完成

        return "teacher/index";
    }

    @RequestMapping("/profile.do")
    public String profile(Model model, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        model.addAttribute("teacher", teacher);

        return "teacher/profile";
    }

    public String updateProfile(Model model, HttpSession httpSession,
                                //TODO 参数结合页面搞一下
                                @RequestParam("tEmail")String tEmail) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");


        return "forward:/teacher/profile.do";
    }


}
