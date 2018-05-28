package com.springboot.racemanage.controller;


import com.springboot.racemanage.po.*;
import com.springboot.racemanage.service.*;
import com.springboot.racemanage.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/teacher")
@Transactional
public class TeacherController {

    private static final String MENU_SELECTED_1 = "menuSelected1";
    private static final String RACE_INFO_LIST = "raceInfoList";
    private static final String MENU_SELECTED_2 = "menuSelected2";
    private static final String RACE_MANAGE = "raceManage";
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
        Term term = (Term) httpSession.getAttribute("term");
        List<Raceinfo> raceinfoList = raceinfoService.findByStatusAndTerm(1, term.getTerm());
        model.addAttribute("raceinfoList", raceinfoList);
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
        List<Project> projectList = projectService.findCanRaceProject(teacher.gettUuid(), term.getTerm(), raceInfoUUID);
        model.addAttribute("raceInfo", raceinfo);
        model.addAttribute("projectList", projectList);
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

        raceService.insertSelective(race);

        return "forward:/teacher/teacInfoList.do";
    }

    @RequestMapping("/myRaceList.do")
    public String raceList(Model model, HttpSession httpSession) {

        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Term term = (Term) httpSession.getAttribute("term");
        List<Race> raceList = raceService.findByStatusAndTermAndTUuid(1, term.getTerm(), teacher.gettUuid());
        model.addAttribute("myRaceList", raceList);
        return "teacher/myRaceList";

    }

    @RequestMapping("/raceDetail.do")
    public String raceDetail(Model model, HttpSession httpSession,
                             @RequestParam("raceUUID") String raceUUID) {
        Race race = raceService.findByUuid(raceUUID);
        List<Teamer> teamerList = teamerService.findByStatusAndProUuid(1, race.getProUuid());
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(race.getRaceinfoUuid());
        model.addAttribute("race", race);
        model.addAttribute("teamerList", teamerList);
        model.addAttribute("raceinfo", raceinfo);
        return "teacher/raceDetail";
    }


    @RequestMapping("/updateRace.do")
    public String updateRace(Model model, HttpSession httpSession,
                             @RequestParam("raceUUID") String raceUUID,
                             @RequestParam(value = "file1", required = false) MultipartFile file1,
                             @RequestParam(value = "file2", required = false) MultipartFile file2,
                             @RequestParam(value = "file3", required = false) MultipartFile file3) throws IOException {
        Race race = raceService.findByUuid(raceUUID);
        if (file1 != null) {
            String file1Path = UploadFile.upload(file1, "uploadFile/raceFile/");
            race.setFile1(file1Path);
        }
        if (file2 != null) {
            String file2Path = UploadFile.upload(file2, "uploadFile/raceFile/");
            race.setFile2(file2Path);
        }
        if (file3 != null) {
            String file3Path = UploadFile.upload(file3, "uploadFile/raceFile/");
            race.setFile3(file3Path);
        }

        raceService.update(race);
        return "forward:/teacher/myRaceList.do";

    }

    @RequestMapping("/achievementList.do")
    public String achievementList(Model model, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        List<Race> achivList = raceService.findByStatusAndTUuidAndProgress(1, teacher.gettUuid(), 1);
        model.addAttribute("achivList", achivList);
        return "teacher/achievementList";
    }

    @RequestMapping("/achievementDetail.do")
    public String achievementDetail(Model model,
                                    @RequestParam("raceUUID") String raceUUID) {
        Race race = raceService.findByUuid(raceUUID);
        Raceinfo raceinfo = raceinfoService.findFirstByUuid(race.getRaceinfoUuid());
        Project project = projectService.findFirstByUuid(race.getProUuid());
        List<Teamer> teamerList = teamerService.findByStatusAndProUuid(1, project.getUuid());
        Teacher raceTeacher = teacherService.findByTUuid(race.gettUuid());
        model.addAttribute("race", race);
        model.addAttribute("raceinfo", raceinfo);
        model.addAttribute("teamerList", teamerList);
        model.addAttribute("raceTeacher", raceTeacher);
        return "teacher/achievementDetail";
    }

    @RequestMapping("/toAddProject.do")
    public String toAddProject(Model model, HttpSession httpSession) {
        List<Student> stuList = studentService.findByStuStatus(1);
        model.addAttribute("stuList", stuList);
        return "teacher/addProject";
    }

    @RequestMapping("/addProject.do")
    public String addProject(Model model, HttpSession httpSession,
                             @RequestParam("proName") String proName,
                             @RequestParam("proDescription") String proDescription,
                             @RequestParam(value = "document", required = false) MultipartFile document,
                             @RequestParam("stuUuid[]") List<String> stuUUIDList) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Project project = new Project();
        project.setName(proName);
        project.setDescription(proDescription);
        project.setUuid(UUID.randomUUID().toString());
        project.setDocument("");//后期处理文件上传一并解决
        project.settUuid(teacher.gettUuid());
        project.setTname(teacher.gettName());
        project.setStatus(1);
        projectService.insertSelective(project);

        Invite stuInvite = new Invite();
        stuUUIDList.forEach(tostuuuid -> {
            stuInvite.setDuty("1");
            stuInvite.setTeamerDescription(project.getDescription());
            stuInvite.setFromUuid(teacher.gettUuid());
            stuInvite.setUuid(UUID.randomUUID().toString());
            stuInvite.setProname(project.getName());
            stuInvite.setProUuid(project.getUuid());
            stuInvite.setSendtime(new Date());
            stuInvite.setToUuid(tostuuuid);
            stuInvite.setStatus(1);
            inviteService.insertSelective(stuInvite);
        });

        return "redirect:/teacher/projectList.do";
    }

    @RequestMapping("/projectList.do")
    public String projectList(Model model, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        List<Project> projectList = projectService.findByStatusAndTUuid(1, teacher.gettUuid());
        model.addAttribute("projectList", projectList);
        return "teacher/projectList";
    }

    @RequestMapping("/projectDetail.do")
    public String projectDetail(Model model, HttpSession httpSession,
                                @RequestParam("proUUID") String proUUID) {
        Project project = projectService.findByUuid(proUUID).get(0);
        List<Task> allTaskList = taskService.findByProUuidAndStatusNot(proUUID,0);
        List<Teamer> proTeamerList = teamerService.findByStatusAndProUuid(1, proUUID);
        model.addAttribute("project", project);
        model.addAttribute("allTaskList", allTaskList);
        model.addAttribute("proTeamerList", proTeamerList);
        return "teacher/projectDetail";
    }

    @RequestMapping("/projectTimeLine.do")
    public String projectTimeLine(Model model, HttpSession httpSession,
                                  @RequestParam("projectUUID") String projectUUID) {

        Project project = projectService.findFirstByUuid(projectUUID);

        //名字太不优雅  回来想到更好的就改下
        List<Map> logandteamernameList = logService.getLogAndTeamerNameByProUuid(projectUUID);

        model.addAttribute("logandteamernameList", logandteamernameList);
        model.addAttribute("project", project);
        return "teacher/projectTimeLine";
    }

    @RequestMapping("/toPublishTask.do")
    public String toPublishTask(Model model ,HttpSession httpSession,
                                @RequestParam("proUUID")String proUUID,
                                @RequestParam("teamerUUID")String teamerUUID) {
        Project project = projectService.findFirstByUuid(proUUID);
        List<Teamer> teamerList = teamerService.findByStatusAndProUuid(1,proUUID);
        Teamer teamer = teamerService.findFirstByUuid(teamerUUID);
        model.addAttribute("project", project);
        model.addAttribute("teamerList", teamerList);
        model.addAttribute("teamer", teamer);
        return "teacher/publishTask";
    }

    //给多人分配同一个任务不可行 没意义
//    @RequestMapping("/publishTask.do")
//    public String publishTask(Model model,HttpSession httpSession,
//                              @RequestParam("title")String title,
//                              @RequestParam("description")String description,
//                              @RequestParam("teamerUuid[]")List<String> teamerUUIDList) {
//        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
//
//    }


    @RequestMapping("/publishTask.do")
    public String publishTask(HttpSession httpSession,
                              @RequestParam("title")String title,
                              @RequestParam(value = "description",required = false)String description,
                              @RequestParam("teamerUUID")String teamerUUID,
                              @RequestParam(value = "file1",required = false)MultipartFile file1) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Teamer teamer = teamerService.findFirstByUuid(teamerUUID);
        Task task = new Task();
        task.setDescription(description);
        task.setFile1(file1.getOriginalFilename());
        task.setFromUuid(teacher.gettUuid());
        task.setProUuid(teamer.getProUuid());
        task.setStatus(2);
        task.setTitle(title);
        task.setToUuid(teamerUUID);
        task.setUuid(UUID.randomUUID().toString());
        taskService.insertSelective(task);
        return "forward:/teacher/projectDetail.do?proUUID=" + teamer.getProUuid();
    }

    @RequestMapping("/toReviewTask.do")
    public String toReviewTask(Model model , HttpSession httpSession,
                               @RequestParam("taskUUID")String taskUUID) {
        Task task = taskService.findFirstByUuid(taskUUID);
        Solution solution = solutionService.findByResultAndStatusAndTaskUuid(3, 1, taskUUID);
        Teamer teamer = teamerService.findFirstByUuid(task.getToUuid());
        model.addAttribute("solution", solution);
        model.addAttribute("task", task);
        model.addAttribute("teamer", teamer);
        return "teacher/reviewTask";
    }

    @RequestMapping("/passTask.do")
    public String passTask(HttpSession httpSession,
                           @RequestParam("solutionUUID")String solutionUUID) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Solution solution = solutionService.findByUuid(solutionUUID);
        Task task = taskService.findFirstByUuid(solution.getTaskUuid());
        Teamer teamer = teamerService.findFirstByUuid(task.getToUuid());
        Project project = projectService.findFirstByUuid(task.getProUuid());
        Log log = new Log();
        //以系统身份任务完成消息
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFromUuid("000");
        String msg = teacher.gettName() + "通过了您  " + solution.getTitle() + "  的解决方案。";
        message.setContent(msg);
        message.setSendtime(new Date());
        message.setTitle("系统提示");
        message.setToUuid(teamer.getStuUuid());
        message.setStatus(1);

        //改变任务状态和解决方案状态
        solution.setResult(1);
        task.setProgress(1);

        //新建log
        log.setAction("完成项目任务");
        log.setDescription("完成 "+task.getTitle());
        log.setProUuid(project.getUuid());
        log.setTasktitle(task.getTitle());
        log.setTaskUuid(task.getUuid());
        log.setTeamerUuid(teamer.getUuid());
        log.setTheTime(new Date());
        log.setUuid(UUID.randomUUID().toString());

        messageService.insertSelective(message);
        solutionService.update(solution);
        taskService.update(task);
        logService.insertSelective(log);
        return "forward:/teacher/projectDetail.do?proUUID=" + project.getUuid();
    }

    @RequestMapping("/rejectTask.do")
    public String rejectTask(HttpSession httpSession,
                             @RequestParam("solutionUUID")String solutionUUID) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Solution solution = solutionService.findByUuid(solutionUUID);
        Task task = taskService.findFirstByUuid(solution.getTaskUuid());
        Teamer teamer = teamerService.findFirstByUuid(task.getToUuid());
        Project project = projectService.findFirstByUuid(task.getProUuid());

        //以系统身份发送任务不通过消息
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFromUuid("000");
        String msg = teacher.gettName() + "没有通过您  " + solution.getTitle() + "  的解决方案。";
        message.setContent(msg);
        message.setSendtime(new Date());
        message.setTitle("系统提示");
        message.setToUuid(teamer.getStuUuid());
        message.setStatus(1);

        //改变任务状态和解决方案状态
        solution.setResult(2);
        task.setProgress(2);

        messageService.insertSelective(message);
        solutionService.update(solution);
        taskService.update(task);
        return "forward:/teacher/projectDetail.do?proUUID=" + project.getUuid();
    }

    @RequestMapping("/inviteList.do")
    public String inviteList(Model model, HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        List<Invite> inviteList = inviteService.findByToUuidAndStatus(teacher.gettUuid(), 1);
        System.out.println(inviteList);

        model.addAttribute("inviteList", inviteList);
        return "teacher/inviteList";
    }

    @RequestMapping("/acceptInvite.do")
    public String acceptInvite(Model model, HttpSession httpSession,
                               @RequestParam("inviteUUID")String inviteUUID) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Invite invite = inviteService.findByUuid(inviteUUID);
        Project project = projectService.findFirstByUuid(invite.getProUuid());

        //以系统身份发送邀请接受消息
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFromUuid("000");
        String rfusMsg ="教师"+ teacher.gettName() + "接受了您  " + project.getName() + "  的项目邀请。";
        message.setContent(rfusMsg);
        message.setSendtime(new Date());
        message.setTitle("系统提示");
        message.setToUuid(invite.getFromUuid());
        message.setStatus(1);

        //修改邀请信息状态
        invite.setStatus(2);

        //修改项目指导教师
        project.settUuid(teacher.gettUuid());
        project.setTname(teacher.gettName());

        inviteService.update(invite);
        messageService.insertSelective(message);
        projectService.update(project);
        return "forward:/teacher/inviteList.do";
    }

    @RequestMapping("/refuseInvite.do")
    public String refuseInvite(Model model, HttpSession httpSession,
                               @RequestParam("inviteUUID") String inviteUUID) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Invite invite = inviteService.findByUuid(inviteUUID);
        Project project = projectService.findFirstByUuid(invite.getProUuid());

        //以系统身份发送邀请被拒绝消息
        Message message = new Message();
        message.setUuid(UUID.randomUUID().toString());
        message.setFromUuid("000");
        String rfusMsg = "教师"+ teacher.gettName() + "拒绝了您  " + project.getName() + "  的项目邀请。";
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

        return "forward:/teacher/inviteList.do";
    }


    @RequestMapping("/ignoreInvite.do")
    public String ignoreInvite(Model model, HttpSession httpSession,
                               @RequestParam("inviteUUID") String inviteUUID) {

        Invite invite = inviteService.findByUuid(inviteUUID);

        invite.setStatus(4);
        inviteService.update(invite);

        return "forward:/teacher/inviteList.do";
    }

    @RequestMapping("/msgCenter.do")
    public String msgCenter(Model model , HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        List<Message> messageList = messageService.findByToUuidAndStatus(teacher.gettUuid(), 1);
        List<Map<String, String>> mapList = messageService.getMsgWithStuName(teacher.gettUuid());
        System.out.println("_____" + mapList.size());
        model.addAttribute("msgList", messageList);
        model.addAttribute("mapList", mapList);
        return "teacher/messageCenter";
    }

}
