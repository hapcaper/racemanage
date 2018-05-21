package com.springboot.racemanage.controller;


import com.springboot.racemanage.po.*;
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
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/teacher")
@Transactional
public class TeacherController {

    public static final String MENU_SELECTED_1 = "menuSelected1";
    public static final String RACE_INFO_LIST = "raceInfoList";
    public static final String MENU_SELECTED_2 = "menuSelected2";
    public static final String RACE_MANAGE = "raceManage";
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

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
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

    @RequestMapping("logout.do")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("teacher");
        httpSession.removeAttribute("term");
        return "login";
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
                                @RequestParam(value = "tEmail", required = false) String tEmail,
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
        model.addAttribute(MENU_SELECTED_1, RACE_MANAGE);
        model.addAttribute(MENU_SELECTED_2, RACE_INFO_LIST);
        Term term = (Term) httpSession.getAttribute("term");
        List<Raceinfo> raceinfoList = raceinfoService.findByStatusAndTerm(1, term.getTerm());
        model.addAttribute("raceinfoList", raceinfoList);
        return "teacher/raceInfoList";
    }

    @RequestMapping("/raceInfoDetail.do")
    public String raceInfoDetail(Model model, HttpSession httpSession,
                                 @RequestParam("raceInfoUUID") String raceInfoUUID) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Term term = (Term) httpSession.getAttribute("term");
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(raceInfoUUID);
        System.out.println("[[[[[[[[");
        System.out.println(term);
        System.out.println(raceInfoUUID);
        System.out.println(teacher);
        System.out.println("]]]]]]]]]]]");
        List<Project> projectList = projectService.findCanRaceProject(teacher.gettUuid(), term.getTerm(), raceInfoUUID);
        model.addAttribute("raceInfo", raceinfo);
        model.addAttribute("projectList", projectList);
        System.out.println("{{{{{{{{{{{{{");
        System.out.println(projectList);
        System.out.println("}}}}}}}}}}}}");
        return "teacher/raceInfoDetail";
    }

    @RequestMapping("/addRace.do")
    public String addRace(Model model, HttpSession httpSession,
                          @RequestParam("projectUUID") String projectUUID,
                          @RequestParam("raceInfoUUID") String raceInfoUUID) {
        Project project = projectService.findFirstByUuid(projectUUID);
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(raceInfoUUID);
        Term term = (Term) httpSession.getAttribute("term");
        if (projectUUID == null || projectUUID.length() == 0) {
            System.out.println("项目不能为空");
            return "forward:/teacher/raceInfoDetail.do?raceInfoUUID=" + raceInfoUUID;
        }
        Race race = new Race();
        race.setDescription(raceinfo.getDescription());
        race.setKind(raceinfo.getKind());
        race.setProname(project.getName());
        race.setProUuid(project.getUuid());
        race.setRaceinfoUuid(raceInfoUUID);
        race.setRacename(raceinfo.getRacename());
        race.setRaceteacher(project.getTname());
        race.setStatus(1);
        race.setTerm(term.getTerm());
        race.settUuid(project.gettUuid());
        race.setUuid(UUID.randomUUID().toString());

        System.out.println(";;;;;;" + race);
        raceService.insertSelective(race);

        return "forward:/teacher/teacInfoList.do";
    }

    @RequestMapping("/myRaceList.do")
    public String raceList(Model model, HttpSession httpSession) {

        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Term term = (Term) httpSession.getAttribute("term");
        List<Race> raceList = raceService.findByStatusAndTermAndTUuid(1, term.getTerm(), teacher.gettUuid());
        System.out.println("{{{{{{{");
        System.out.println(raceList);
        System.out.println("}}}}}}}}}}}}");
        model.addAttribute("myRaceList", raceList);
        return "teacher/myRaceList";

    }

}
