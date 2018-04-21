package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.*;
import com.springboot.racemanage.service.*;
import com.springboot.racemanage.util.Tools;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 这里leader就是headteacher(竞赛组长)
 */
@Controller
@RequestMapping("/leader")
@Transactional
public class LeaderController {
    private static final String PEOPLA_MANAGE = "peoplaManage";
    private static final String STUDENT_MANAGE = "studentManage";
    private static final String TEACHER_MANAGE = "teacherManage";
    private static final String MENU_SELECTED_1 = "menuSelected1";
    private static final String MENU_SELECTED_2 = "menuSelected2";
    private static final String RACE_MANAGE = "raceManage";
    private static final String RACE_LIST = "raceList";
    private static final String ADD_RACE_INFO = "addRaceInfo";
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

    @RequestMapping("/teacherManage.do")
    public String teaherManage(Model model) {
        model.addAttribute(MENU_SELECTED_1, PEOPLA_MANAGE);
        model.addAttribute(MENU_SELECTED_2, TEACHER_MANAGE);

        List<Teacher> teacherList = teacherService.findByTStatus(1);
        model.addAttribute("teacherList", teacherList);

        return "leader/teacherManage";
    }

    @RequestMapping("/studentManage.do")
    public String studentManage(Model model) {
        model.addAttribute(MENU_SELECTED_1, PEOPLA_MANAGE);
        model.addAttribute(MENU_SELECTED_2, STUDENT_MANAGE);

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

    @RequestMapping("/toAddRaceInfo.do")
    public String toAddRaceInfo(Model model) {
        model.addAttribute(MENU_SELECTED_1,RACE_MANAGE );
        model.addAttribute(MENU_SELECTED_2, ADD_RACE_INFO);

        return "leader/addRaceInfo";
    }
    @RequestMapping("/addRaceInfo.do")
    public String addRaceInfo(Model model,HttpSession httpSession,
                              @RequestParam(value = "racename",required = false,defaultValue = "")String racename,
                              @RequestParam(value = "kind",required = false,defaultValue = "")String kind,
                              @RequestParam(value = "timeRange",required = false,defaultValue = "")String timeRange,
                              @RequestParam(value = "file",required = false)MultipartFile file,
                              @RequestParam(value = "description",required = false,defaultValue = "")String description) throws ParseException {

        Term term = (Term) httpSession.getAttribute("term");
        Raceinfo raceinfo = new Raceinfo();
        raceinfo.setDescription(description);
        raceinfo.setRacename(racename);
        raceinfo.setKind(kind);
        raceinfo.setUuid(UUID.randomUUID().toString());
        raceinfo.setTerm(term.getTerm());

        //dates begainDate: 开始日期  endDate:结束日期
        Map<String , Date> dates = Tools.dateRangeTransform(timeRange);
        raceinfo.setBegaintime(dates.get("begainDate"));
        raceinfo.setEndtime(dates.get("endDate"));


//        raceinfo.setBegaintime();
//        raceinfo.setEndtime();

        System.out.println(raceinfo);
        System.out.println(timeRange);
        if (file != null) {
            System.out.println(file.getOriginalFilename());
            String fileUrl = null;
            try {
                fileUrl = UploadFile.upload(file,"src\\main\\resources\\templates\\uploadFiles\\raceInfoFile\\");

            } catch (IOException e) {
                System.out.println("赛事文件上传失败");
                e.printStackTrace();
            }
            raceinfo.setFile1(fileUrl);
        }

        new RuntimeException();
        System.out.println(raceinfo);
        raceinfoService.insertSelective(raceinfo);
        return "redirect:/leader/raceInfoList.do";
    }

    //TODO
    @RequestMapping("/raceInfoList.do")
    public String raceInfoList(Model model,HttpSession httpSession) {

        return "leader/raceInfoList";
    }

    //TODO
    @RequestMapping("/raceList.do")
    public String raceList(Model model,HttpSession httpSession) {
        model.addAttribute(MENU_SELECTED_1, RACE_MANAGE);
        model.addAttribute(MENU_SELECTED_2, RACE_LIST);
        Term term = (Term) httpSession.getAttribute("term");

        List<Race> raceList = raceService.findByStatusAndTerm(1, term.getTerm());
        model.addAttribute("raceList", raceList);

        return "leader/raceList";
    }

    //TODO
    @RequestMapping("/projectList.do")
    public String projectList() {

        return "leader/projectList";
    }

}
