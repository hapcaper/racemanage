package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.Leader;
import com.springboot.racemanage.po.Term;
import com.springboot.racemanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 这里leader就是headteacher
 */
@Controller
@RequestMapping("/leader")
@Transactional
public class LeaderController {
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

    @Autowired
    LeaderService leaderService;


    @RequestMapping("/login.do")
    public String login(Model model, HttpSession httpSession,
                        @RequestParam("username")String usernumber,
                        @RequestParam("password")String password) {
        Leader leader = leaderService.findByLStatusAndLPasswordAndLNumber(1, password, usernumber);
        if (leader == null) {       //leader不存在
            String errmsg = "账号或密码错误";
            model.addAttribute("errmsg", errmsg);
            return "login";
        }
        Term term = termService.findFirstByStatusOrderByTerm(1);
        httpSession.setAttribute("leader", leader);
        httpSession.setAttribute("term",term);

        return "leader/index";
    }

}
