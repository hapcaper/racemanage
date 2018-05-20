package com.springboot.racemanage.controller;


import com.springboot.racemanage.po.Project;
import com.springboot.racemanage.po.Raceinfo;
import com.springboot.racemanage.po.Teacher;
import com.springboot.racemanage.po.Term;
import com.springboot.racemanage.service.*;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(Model model, HttpSession httpSession,
                        @RequestParam("username") String tNumber,
                        @RequestParam("password") String password) {
        Teacher teacher = teacherService.login(1, tNumber, password);

        if (teacher == null) {
            model.addAttribute("errMsg", "工号或密码错误");
            return "login";
        } else {
            Term term = termService.findFirstByStatusOrderByTerm(1);
            httpSession.setAttribute("term", term);
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

    @RequestMapping("/updateProfile.do")
    public String updateProfile(Model model, HttpSession httpSession,
                                @RequestParam(value = "tEmail",required = false) String tEmail,
                                @RequestParam("phone") String phone,
                                @RequestParam("oldPasswd") String oldPasswd,
                                @RequestParam("newPasswd") String newPasswd,

                                //TODO 上传文件需要重新再搞下

                                @RequestParam("photo") MultipartFile photo) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        if (oldPasswd.equals(teacher.gettPassword())) {
            teacher.settEmail(tEmail);
            teacher.settPhone(phone);
            teacher.settPassword(newPasswd);
            model.addAttribute("updateMsg", "更新成功");
            return "forward:/teacher/profile.do";

        } else if (oldPasswd.length() == 0) {
            teacher.settEmail(tEmail);
            teacher.settPhone(phone);
            model.addAttribute("updateMsg", "更新成功");
            return "forward:/teacher/profile.do";
        } else {
            model.addAttribute("updateMsg", "更新失败");
            return "forward:/teacher/profile.do";
        }
    }

    @RequestMapping("raceInfoList.do")
    public String raceInfoList(Model model, HttpSession httpSession) {
        Term term = (Term) httpSession.getAttribute("term");
        List<Raceinfo> raceinfoList = raceinfoService.findByStatusAndTerm(1, term.getTerm());
        model.addAttribute("raceinfoList", raceinfoList);
        return "teacher/raceInfoList";
    }

    @RequestMapping("/raceInfoDetail.do")
    public String raceInfoDetail(Model model,HttpSession httpSession,
                                 @RequestParam("raceInfoUUID")String raceInfoUUID) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(raceInfoUUID);
        List<Project> projectList = projectService.findByStatusAndTUuid(1, teacher.gettUuid());
        //TODO 检查是否可以报名
        model.addAttribute("raceInfo", raceinfo);
        model.addAttribute("projectList", projectList);
        return "teacher/raceInfoDetail";
    }

    @RequestMapping("/addRace.do")
    public String addRace(Model model, HttpSession httpSession,
                          @RequestParam("projectUUID") String projectUUID) {
        //TODO 参照学生搞一下  有点小麻烦

        return "forward:/teacher/teacInfoList.do";
    }

}
