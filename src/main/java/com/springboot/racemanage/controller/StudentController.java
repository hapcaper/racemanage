package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.*;
import com.springboot.racemanage.service.*;
import com.springboot.racemanage.service.serviceImpl.RaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 学生端的控制器
 * 有空优化下代码 有些乱
 */
@Controller
@RequestMapping("/student")
@Transactional
public class StudentController {

    public static final String MENU_SELECTED_1 = "menuSelected1";
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
    public String login(HttpSession httpSession, Model model, @RequestParam("stunumber") String stunumber,
                        @RequestParam("password") String password) {
        model.addAttribute(MENU_SELECTED_1, "index");
        Student student = studentService.findFirstByStuNumberAndStuPasswordAndStuStatus(stunumber, password, 1);
        List<Teamer> teamerList = teamerService.findByStuUuid(student.getStuUuid());
        System.out.println(teamerList);
        if (student != null) {
            Term term = termService.findFirstByStatusOrderByTerm(1);
            httpSession.setAttribute("term", term);
            httpSession.setAttribute("student", student);
            httpSession.setAttribute("teamerList", teamerList);
            return "student/index";
        } else {
            model.addAttribute("errorMsg", "学号或密码错误!!");
            return "login";
        }
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("student");
        httpSession.removeAttribute("term");
        httpSession.removeAttribute("teamerList");
        return "login";
    }

    @RequestMapping("/index.do")
    public String index(Model model) {
        model.addAttribute("menuSelected1", "index");

        return "student/index";
    }

    @RequestMapping("/profile.do")
    public String profile(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "profile");
        Student student = (Student) httpSession.getAttribute("student");
        Integer proNum = teamerService.countByStuUuid(student.getStuUuid());
        Integer inviteNum = inviteService.countByToUuidAndStatus(student.getStuUuid(), 1);
        Integer msgNum = messageService.countByToUuidAndStatus(student.getStuUuid(), 1);
        model.addAttribute("inviteNum", inviteNum).addAttribute("msgNum", msgNum);
        model.addAttribute("proNum", proNum);
        model.addAttribute("student", student);
        return "student/profile";
    }


    @RequestMapping(value = "/updateProfile.do", method = RequestMethod.POST)
    public String updateProfile(Model model, HttpSession httpSession,
                                @Nullable @RequestParam("email") String email,
                                @Nullable @RequestParam("phone") String phone,
                                @Nullable @RequestParam("oldPasswd") String oldPasswd,
                                @Nullable @RequestParam("newPasswd") String newPasswd,
                                @Nullable @RequestParam("photo") MultipartFile photo) {
        model.addAttribute("menuSelected1", "profile");
        Student student = (Student) httpSession.getAttribute("student");
        if (phone.length() != 0) {
            student.setPhoto("");
        }
        if (email.length() != 0) {
            student.setStuEmail(email);
        }

        String passwdMsg = "OK";
        if (newPasswd.length() >= 6) {
            student.setStuPassword(newPasswd);
        } else {
            passwdMsg = "密码长度必须不小于6位";
        }
        if (phone.length() != 0) {
            student.setStuPhone(phone);
        }
        int a = studentService.update(student);
        String updateMsg = "更新成功";
        if (a == 0) {
            updateMsg = "更新失败";
        }
        Integer proNum = teamerService.countByStuUuid(student.getStuUuid());
        Integer inviteNum = inviteService.countByToUuidAndStatus(student.getStuUuid(), 1);
        Integer msgNum = messageService.countByToUuidAndStatus(student.getStuUuid(), 1);
        model.addAttribute("inviteNum", inviteNum).addAttribute("msgNum", msgNum);
        model.addAttribute("proNum", proNum);
        model.addAttribute("student", student);
        model.addAttribute("updateMsg", updateMsg);
        model.addAttribute("passwdMsg", passwdMsg);

        System.out.println(student + "--------------------");

        return "student/profile";

    }

    @RequestMapping("/toAddProject.do")
    public String toAddProject(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "projectManage");
        model.addAttribute("menuSelected2", "addProject");
        Student student = (Student) httpSession.getAttribute("student");
        List<Student> stuList = studentService.findStuUuidAndStuNameByStuUuidNot(student.getStuUuid());
        System.out.println(stuList);
        for (Student stu : stuList) {
            System.out.println(stu);
        }
        List<Teacher> teList = teacherService.findTNameAndTUuid();
        System.out.println(teList);
        model.addAttribute("stuList", stuList);
        model.addAttribute("teList", teList);
        return "student/addProject";
    }

    @RequestMapping(value = "/addProject.do", method = RequestMethod.POST)
    public String addProject(Model model, HttpSession httpSession,
                             @RequestParam("proName") String proName,
                             @RequestParam("proDescription") String proDescription,
                             @RequestParam("document") MultipartFile document,
                             @RequestParam("stuUuid[]") List<String> stuUuids,
                             @RequestParam("teUuid") String teUuid) throws IOException {
        Student student = (Student) httpSession.getAttribute("student");
        Project project = new Project();
        project.setUuid(UUID.randomUUID().toString());
        project.setName(proName);
        project.setDescription(proDescription);
        project.settUuid(teUuid);
        project.setHeadman(student.getStuUuid());

        //得到文件名并拼接UUID ， 防止文件重名
        StringBuffer docName = new StringBuffer(UUID.randomUUID().toString());
        docName.append("_" + document.getOriginalFilename());

        StringBuffer docPath = new StringBuffer("src\\main\\resources\\templates\\uploadFiles\\projectDoc\\p");
        docPath.append(docName.toString());

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(docPath.toString())));
        out.write(document.getBytes());
        out.flush();
        out.close();

        StringBuffer docPath2 = docPath;
        docPath2.replace(0, 19, "");
        System.out.println(docPath2);
        project.setDocument(docPath2.toString());

        Teamer teamer = new Teamer();
        teamer.setDuty("队长");
        teamer.setProame(project.getName());
        teamer.setProUuid(project.getUuid());
        teamer.setStatus(1);
        teamer.setStuname(student.getStuName());
        teamer.setStuUuid(student.getStuUuid());
        teamer.setUuid(UUID.randomUUID().toString());
        teamerService.insertSelective(teamer);

        //发送学生邀请
        Invite stuInvite = new Invite();
        for (String tostuuuid : stuUuids) {
            stuInvite.setId(null);
            stuInvite.setDuty("1");
            stuInvite.setTeamerDescription(project.getDescription());
            stuInvite.setFromUuid(student.getStuUuid());
            stuInvite.setUuid(UUID.randomUUID().toString());
            stuInvite.setProname(project.getName());
            stuInvite.setProUuid(project.getUuid());
            stuInvite.setSendtime(new Date());
            stuInvite.setToUuid(tostuuuid);
            stuInvite.setStatus(1);
            System.out.println("****----****" + stuInvite);
            inviteService.insertSelective(stuInvite);
        }

        //发送教师邀请
        Invite teInvite = new Invite();
        teInvite.setStatus(1);
        teInvite.setToUuid(teUuid);
        teInvite.setSendtime(new Date());
        teInvite.setProUuid(project.getUuid());
        teInvite.setProname(project.getName());
        teInvite.setUuid(UUID.randomUUID().toString());
        teInvite.setFromUuid(student.getStuUuid());
        teInvite.setTeamerDescription(project.getDescription());
        teInvite.setDuty("2");
        inviteService.insertSelective(teInvite);

        projectService.insertSelective(project);


//        File d = new File(docPath,docName.toString());
//        try {
//            document.transferTo(d);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("============" + httpSession.getServletContext().getContextPath());
        System.out.println("666666666666" + document.getOriginalFilename());
        System.out.println("55555555555555555555" +
                httpSession.getServletContext().getRealPath("static\\uploadFiles\\projectDoc"));

//        try {
//
//            OutputStream outputStream = new FileOutputStream("");
//            outputStream.write(document.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return "redirect:/student/projectList.do";
        return "redirect:/student/projectList1.do";
    }

    @RequestMapping("/projectList1.do")
    public String projectList(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "projectManage");
        model.addAttribute("menuSelected2", "projectList");
        Student student = (Student) httpSession.getAttribute("student");

        List<Teamer> teamerList = teamerService.findByStatusAndStuUuid(1, student.getStuUuid());
        List<Project> projectList = new ArrayList<>();
        for (Teamer t : teamerList) {
            projectList.add(projectService.findFirstByUuid(t.getProUuid()));
        }

        System.out.println(teamerList);
        System.out.println(projectList.size());
        System.out.println(projectList);
        model.addAttribute("projectList", projectList);
        return "student/projectList";
    }

    @RequestMapping("/projectDetail.do")
    public String projectDetail(Model model, HttpSession httpSession,
                                @RequestParam("proUUID") String proUUID) {
        model.addAttribute("menuSelected1", "projectManage");
        Student student = (Student) httpSession.getAttribute("student");
        Project project = projectService.findFirstByUuid(proUUID);
        System.out.println(project);
        Teamer myTeamer = teamerService.findFirstByStatusAndStuUuidAndProUuid(1, student.getStuUuid(), proUUID);
        List<Task> myTaskList = taskService.findByToUuidAndStatusNot(myTeamer.getUuid(), 0);
        List<Teamer> proTeamerList = teamerService.findByStatusAndProUuid(1, proUUID);

        model.addAttribute("myTeamer", myTeamer);
        model.addAttribute("project", project);
        model.addAttribute("myTaskList", myTaskList);
        model.addAttribute("proTeamerListt", proTeamerList);
        return "student/projectDetail";
    }

    @RequestMapping("/toSubmitTask.do")
    public String toSubmitTask(Model model, @RequestParam("taskUUID") String taskUUID) {

        Task task = taskService.findFirstByUuid(taskUUID);
        model.addAttribute("task", task);
        return "student/submitTask";
    }

    @RequestMapping(value = "/postSolution.do", method = RequestMethod.POST)
    public String postSolution(Model model, HttpSession httpSession,
                               @RequestParam("title") String title,
                               @RequestParam("taskUUID") String taskUUID,
                               @RequestParam("content") String content,
                               @RequestParam("file1") MultipartFile file1) throws IOException {
        Student student = (Student) httpSession.getAttribute("student");
        Solution solution = new Solution();
        solution.setUuid(UUID.randomUUID().toString());
        solution.setTitle(title);
        solution.setContent(content);


        StringBuffer file1Name = new StringBuffer(UUID.randomUUID().toString());
        file1Name.append("_" + file1.getOriginalFilename());
        StringBuffer file1Path = new StringBuffer("src\\main\\resources\\templates\\uploadFiles\\solutionFiles\\");
        file1Path.append(file1Name.toString());
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file1Path.toString())));
        out.write(file1.getBytes());
        out.flush();
        out.close();

        //更改数据库存储文件的路径
        file1Path.replace(0, 19, "");

        solution.setFile1(file1Path.toString());
        solution.setResult(3);
        solution.setStatus(1);
        solution.setStuUuid(student.getStuUuid());
        solution.setTaskUuid(taskUUID);

        solutionService.insertSelective(solution);
        //更新task状态为待审阅
        Task t = taskService.findFirstByUuid(taskUUID);
        t.setProgress(3);
        taskService.update(t);
        //主要是为了得到project的UUID  以后有机会改为sql查询
        Teamer teamer = teamerService.findFirstByUuid(t.getToUuid());
        String proUUID = teamer.getProUuid();


//        model.addAttribute("MENU_SELECTED_", "projectManage");
//        Project project = projectService.findFirstByUuid(proUUID);
//        System.out.println(project);
//        Teamer myTeamer = teamerService.findFirstByStatusAndStuUuidAndProUuid(1, student.getStuUuid(), proUUID);
//        List<Task> myTaskList = taskService.findByToUuidAndStatusNot(myTeamer.getUuid(), 0);
//        List<Teamer> proTeamerList = teamerService.findByStatusAndProUuid(1, proUUID);
//
//        model.addAttribute("myTeamer", myTeamer);
//        model.addAttribute("project", project);
//        model.addAttribute("myTaskList", myTaskList);
//        model.addAttribute("proTeamerListt", proTeamerList);
//        return "student/projectDetail";

        return "redirect:/student/projectDetail.do?proUUID=" + proUUID;
    }

    @RequestMapping("/inviteList.do")
    public String inviteList(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "projectManage");
        model.addAttribute("menuSelected2", "invitation");
        Student student = (Student) httpSession.getAttribute("student");
        List<Invite> inviteList = inviteService.findByToUuidAndStatus(student.getStuUuid(), 1);

        System.out.println(inviteList);

        model.addAttribute("inviteList", inviteList);
        return "student/teamInterview";

    }

    @RequestMapping("/raceInfoList.do")
    public String raceInfoList(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "raceManage");
        model.addAttribute("menuSelected2", "raceInfo");
        Term term = termService.findFirstByStatusOrderByTerm(1);
        List<Raceinfo> raceinfoList = raceinfoService.findByStatusAndTerm(1, term.getTerm());

        model.addAttribute("raceinfoList", raceinfoList);

        return "student/raceInfoList";
    }

    @RequestMapping("/raceInfoDetail.do")
    public String raceInfoDetail(Model model, HttpSession httpSession,
                                 @RequestParam("raceInfoUUID") String raceInfoUUID) {
        Student student = (Student) httpSession.getAttribute("student");

        Raceinfo raceinfo = raceinfoService.findFirstByUuid(raceInfoUUID);
        List<Project> projectList = projectService.getProjectForRaceinfoDetail(student.getStuUuid(), raceInfoUUID);

        Logger.getGlobal().log(Level.WARNING, raceinfo.toString());
        Logger.getGlobal().log(Level.WARNING, projectList.toString());
        model.addAttribute("projectList", projectList);

        model.addAttribute("raceInfo", raceinfo);
        return "student/raceInfoDetail";
    }

    @RequestMapping("/msgCenter.do")
    public String msgCenter(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "messageCenter");
        Student student = (Student) httpSession.getAttribute("student");
        List<Message> messageList = messageService.findByToUuidAndStatus(student.getStuUuid(), 1);
        List<Map<String, String>> mapList = messageService.getMsgWithStuName(student.getStuUuid());
        System.out.println("_____" + mapList.size());
        model.addAttribute("msgList", messageList);
        model.addAttribute("mapList", mapList);

        return "student/messageCenter";
    }

    @RequestMapping("/myRace.do")
    public String myRace(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "raceManage");
        model.addAttribute("menuSelected2", "myRace");
        Student student = (Student) httpSession.getAttribute("student");
        Term term = termService.findFirstByStatusOrderByTerm(1);
        List<Race> myRaceList = raceService.getStuRaceListByTerm(student.getStuUuid(), term.getTerm());

        model.addAttribute("myRaceList", myRaceList);
        return "student/raceList";
    }

    @RequestMapping("/raceDetail.do")
    public String raceDetail(Model model, HttpSession httpSession, @RequestParam("raceUUID") String raceUUID) {
        Race race = raceService.findByUuid(raceUUID);
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(race.getRaceinfoUuid());
        Project project = projectService.findFirstByUuid(race.getProUuid());
        List<Teamer> teamerList = teamerService.findByStatusAndProUuid(1, project.getUuid());

        model.addAttribute("race", race);
        model.addAttribute("raceinfo", raceinfo);
        model.addAttribute("teamerList", teamerList);


        return "student/raceDetail";
    }

    @RequestMapping("/achievementDetail.do")
    public String achievementDetail(Model model, HttpSession httpSession, @RequestParam("raceUUID") String raceUUID) {
        Race race = raceService.findByUuid(raceUUID);
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(race.getRaceinfoUuid());
        Project project = projectService.findFirstByUuid(race.getProUuid());
        List<Teamer> teamerList = teamerService.findByStatusAndProUuid(1, project.getUuid());

        model.addAttribute("race", race);
        model.addAttribute("raceinfo", raceinfo);
        model.addAttribute("teamerList", teamerList);


        return "student/achievementDetail";
    }

    @RequestMapping("/updateRace.do")
    public String updateRace(Model model, HttpSession httpSession,
                             @Nullable @RequestParam("file1") MultipartFile file1,
                             @Nullable @RequestParam("file2") MultipartFile file2,
                             @Nullable @RequestParam("file3") MultipartFile file3,
                             @RequestParam("raceUUID") String raceUUID) throws IOException {
        //得到文件名并拼接UUID ， 防止文件重名
//        StringBuffer docName = new StringBuffer(UUID.randomUUID().toString());
//        docName.append("_"+document.getOriginalFilename());
//
//        StringBuffer docPath = new StringBuffer("src\\main\\resources\\templates\\uploadFiles\\projectDoc\\p");
//        docPath.append(docName.toString());
//
//        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(docPath.toString())));
//        out.write(document.getBytes());
//        out.flush();
//        out.close();
//
//        StringBuffer docPath2 = docPath;
//        docPath2.replace(0, 19, "");
//        System.out.println(docPath2);
//        project.setDocument(docPath2.toString());

        Race race = raceService.findByUuid(raceUUID);

        //TODO 图片上传成了 页面url显示不出来
        if (file1 != null && file1.getOriginalFilename().length() > 0) {
            //添加第一个文件
            StringBuffer file1Name = new StringBuffer(UUID.randomUUID().toString());
            file1Name.append("_" + file1.getOriginalFilename());
            StringBuffer file1Path = new StringBuffer("src\\main\\resources\\templates\\uploadFiles\\projectDoc\\r");
            file1Path.append(file1Name.toString());


            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file1Path.toString())));
            out.write(file1.getBytes());
            out.flush();
            out.close();

            //springboot不能使用transferTo(),可能是因为他的tomcat是运行时产生的
//            file1.transferTo(to);
            race.setFile1("uploadFiles/raceFiles/r" + file1Name.toString());

        }


        if (file2 != null && file2.getOriginalFilename().length() > 0) {
            //添加第二个文件
            StringBuffer file2Name = new StringBuffer(UUID.randomUUID().toString());
            file2Name.append("_" + file2.getOriginalFilename());
            StringBuffer file2Path = new StringBuffer("src\\main\\resources\\templates\\uploadFiles\\raceFiles\\");
            file2Path.append(file2Name.toString());

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file2Path.toString())));
            out.write(file2.getBytes());
            out.flush();
            out.close();

//            file2.transferTo(new File((file2Path.toString())));
            race.setFile2("uploadFiles\\raceFiles\\" + file2Name.toString());
        }


        if (file3 != null && file3.getOriginalFilename().length() > 0) {
            //添加第三个文件
            StringBuffer file3Name = new StringBuffer(UUID.randomUUID().toString());
            file3Name.append("_" + file3.getOriginalFilename());
            StringBuffer file3Path = new StringBuffer("src\\main\\resources\\templates\\uploadFiles\\raceFiles\\");
            file3Path.append(file3Name.toString());

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file3Path.toString())));
            out.write(file3.getBytes());
            out.flush();
            out.close();

//            file3.transferTo(new File((file3Path.toString())));

            StringBuffer file3Path2 = file3Path;
            file3Path2.replace(0, 19, "");
            System.out.println(file3Path2);

            race.setFile3(file3Path2.toString());
        }

        System.out.println(race);
        raceService.update(race);


        return "redirect:/student/raceDetail.do?raceUUID=" + raceUUID;
    }

    @RequestMapping("/acceptInvite.do")
    public String acceptInvite(Model model, HttpSession httpSession,
                               @RequestParam("inviteUUID") String inviteUUID) {

        Student student = (Student) httpSession.getAttribute("student");
        Invite invite = inviteService.findByUuid(inviteUUID);
        Project project = projectService.findFirstByUuid(invite.getProUuid());

        //添加teamer信息
        Teamer teamer = new Teamer();
        teamer.setUuid(UUID.randomUUID().toString());
        teamer.setStuUuid(student.getStuUuid());
        teamer.setStuname(student.getStuName());
        teamer.setStatus(1);
        teamer.setProUuid(invite.getProUuid());
        teamer.setProame(invite.getProname());
        teamer.setDuty("暂定");
        teamer.setDutydescription(invite.getDutydescription());

        //以系统身份发送邀请接受绝消息
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFromUuid("000");
        String rfusMsg = student.getStuName() + "接受了您  " + project.getName() + "  的项目邀请。";
        message.setContent(rfusMsg);
        message.setSendtime(new Date());
        message.setTitle("系统提示");
        message.setToUuid(invite.getFromUuid());
        message.setStatus(1);
        System.out.println("+++++++" + message);

        //修改邀请信息状态
        invite.setStatus(2);

        //执行
        teamerService.insertSelective(teamer);
        inviteService.update(invite);
        messageService.insertSelective(message);
        return "redirect:/student/inviteList.do";
    }


    @RequestMapping("/refuseInvite.do")
    public String refuseInvite(Model model, HttpSession httpSession,
                               @RequestParam("inviteUUID") String inviteUUID) {
        Student student = (Student) httpSession.getAttribute("student");
        Invite invite = inviteService.findByUuid(inviteUUID);
        Project project = projectService.findFirstByUuid(invite.getProUuid());

        //以系统身份发送邀请被拒绝消息
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFromUuid("000");
        String rfusMsg = student.getStuName() + "拒绝了您  " + project.getName() + "  的项目邀请。";
        message.setContent(rfusMsg);
        message.setSendtime(new Date());
        message.setTitle("系统提示");
        message.setToUuid(invite.getFromUuid());
        message.setStatus(1);
        System.out.println("+++++++" + message);

        //修改邀请信息状态
        invite.setStatus(3);
        System.out.println("~~~~~~" + invite);

        inviteService.update(invite);
        messageService.insertSelective(message);
        return "redirect:/student/inviteList.do";
    }

    @RequestMapping("/ignoreInvite.do")
    public String ignoreInvite(Model model, HttpSession httpSession,
                               @RequestParam("inviteUUID") String inviteUUID) {

        Invite invite = inviteService.findByUuid(inviteUUID);

        invite.setStatus(4);
        inviteService.update(invite);

        return "redirect:/student/inviteList.do";
    }

    @RequestMapping("/achievementList.do")
    public String achievementList(Model model, HttpSession httpSession) {
        model.addAttribute("menuSelected1", "achievement");

        Student student = (Student) httpSession.getAttribute("student");
        List<Race> achivList = raceService.getAchivementListByStuUuid(student.getStuUuid());
        System.out.println("$$$$$$$$$" + achivList);

        model.addAttribute("achivList", achivList);
        return "student/achievementList";
    }

    @RequestMapping("/projectTimeLine.do")
    public String projectTimeLine(Model model, HttpSession httpSession,
                                  @RequestParam("projectUUID") String projectUUID) {

        Project project = projectService.findFirstByUuid(projectUUID);

        //名字太不优雅  回来想到更好的就改下
        List<Map> logandteamernameList = logService.getLogAndTeamerNameByProUuid(projectUUID);

        model.addAttribute("logandteamernameList", logandteamernameList);
        model.addAttribute("project", project);
        return "student/projectTimeLine";
    }

    @RequestMapping("/addRace.do")
    public String addRace(Model model, HttpSession httpSession, @RequestParam("projectUUID") String projectUUID,
                          @RequestParam("raceInfoUUID") String raceInfoUUID) {

        Project project = projectService.findFirstByUuid(projectUUID);
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(raceInfoUUID);
        Term term = (Term) httpSession.getAttribute("term");

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
        return "redirect:/student/myRace.do";
    }


}