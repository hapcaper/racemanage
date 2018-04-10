package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.Leader;
import com.springboot.racemanage.po.Student;
import com.springboot.racemanage.po.Term;
import com.springboot.racemanage.service.*;
import com.springboot.racemanage.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 这里leader就是headteacher(竞赛组长)
 */
@Controller
@RequestMapping("/leader")
@Transactional
public class LeaderController {
    public static final String PEOPLA_MANAGE = "peoplaManage";
    public static final String STUDENT_MANAGE = "studentManage";
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
                        @RequestParam("lnumber") String usernumber,
                        @RequestParam("password") String password) {
        Leader leader = leaderService.findByLStatusAndLPasswordAndLNumber(1, password, usernumber);
        if (leader == null) {       //leader不存在
            String errmsg = "账号或密码错误";
            model.addAttribute("errmsg", errmsg);
            return "login";
        }
        Term term = termService.findFirstByStatusOrderByTerm(1);
        httpSession.setAttribute("leader", leader);
        httpSession.setAttribute("term", term);
        model.addAttribute("menuSelected1", "index");
        return "leader/index";
    }

    @RequestMapping("/index.do")
    public String index(Model model) {
        model.addAttribute("menuSelected1", "index");

        return "leader/index";
    }

    @RequestMapping("/profile.do")
    public String profile(Model model) {
        model.addAttribute("menuSelected1", "profile");
        int proNum = projectService.countByStatus(1);
        int teNum = teacherService.countByTStatus(1);
        int stuNum = studentService.countByStuStatus(1);

        model.addAttribute("proNum", proNum);
        model.addAttribute("teNum", teNum);
        model.addAttribute("stuNum", stuNum);

        return "leader/profile";
    }

    @RequestMapping("/updateProfile.do")
    public String updateProfile(Model model, HttpSession httpSession,
                                @Nullable @RequestParam("email") String email,
                                @Nullable @RequestParam("phone") String phone,
                                @Nullable @RequestParam("oldPasswd") String oldPasswd,
                                @Nullable @RequestParam("newPasswd") String newPasswd,
                                @Nullable @RequestParam("photo") MultipartFile photo) {
        Leader leader = (Leader) httpSession.getAttribute("leader");
        leader.setlEmail(email);
        leader.setlPhone(phone);
        if (oldPasswd.equals(leader.getlPassword())) {
            leader.setlPassword(newPasswd);
        }
        String upload = null;
        if (!phone.isEmpty()) {
            try {
                upload = UploadFile.upload(photo, "src\\main\\resources\\templates\\uploadFiles\\userPhoto\\");
            } catch (IOException e) {
                System.out.println("文件上传失败");
                model.addAttribute("uploadfail", "图片上传失败");
                e.printStackTrace();
            }
        }
        leader.setlPhoto(upload);
        leaderService.update(leader);
        httpSession.setAttribute("leader", leader);

        return "redirect:/leader/profile.do";
    }

    //TODO
    @RequestMapping("/teacherManage.do")
    public String teaherManage(Model model) {

        return "leader/teacherManage";
    }

    //TODO
    @RequestMapping("/studentManage.do")
    public String studentManage(Model model) {
        model.addAttribute("menuSelected1", PEOPLA_MANAGE);
        model.addAttribute("menuSelected2", STUDENT_MANAGE);

        List<Student> studentList = studentService.findByStuStatus(1);
        model.addAttribute("studentList", studentList);

        return "leader/studentManage";
    }

    //TODO
    @RequestMapping("/publishAchievement.do")
    public String publishAchievement() {

        return "leader/publishAchievement";
    }

    //TODO
    @RequestMapping("/achievementList.do")
    public String achievementList() {

        return "leader/achievementList";
    }

    //TODO
    @RequestMapping("/toAddRaceInfo.do")
    public String toAddRaceInfo() {

        return "leader/addRaceInfo";
    }

    //TODO
    @RequestMapping("/raceInfoList.do")
    public String raceInfoList() {

        return "leader/raceInfoList";
    }

    //TODO
    @RequestMapping("/raceList.do")
    public String raceList() {

        return "leader/raceList";
    }

    //TODO
    @RequestMapping("/projectList.do")
    public String projectList() {

        return "leader/projectList";
    }

}
