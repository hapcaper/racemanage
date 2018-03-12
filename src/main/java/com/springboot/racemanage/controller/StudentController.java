package com.springboot.racemanage.controller;

import com.springboot.racemanage.po.*;
import com.springboot.racemanage.service.*;
import com.sun.javafx.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/student")
@Transactional
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

    @Autowired
    TaskService taskService;

    @Autowired
    SolutionService solutionService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(HttpSession httpSession,Model model, @RequestParam("stunumber") String stunumber, @RequestParam("password") String password){
        model.addAttribute("menuSelected1", "index");
        Student student = studentService.findFirstByStuNumberAndStuPasswordAndStuStatus(stunumber, password, 1);
        List<Teamer> teamerList = teamerService.findByStuUuid(student.getStuUuid());
        System.out.println(teamerList);
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
        model.addAttribute("menuSelected1","projectManage");
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
        model.addAttribute("teList",teList);
        return "student/addProject";
    }

    @RequestMapping(value = "/addProject.do",method = RequestMethod.POST)
    public String addProject(Model model,HttpSession httpSession,
                             @RequestParam("proName")String proName,
                             @RequestParam("proDescription")String proDescription,
                             @RequestParam("document")MultipartFile document,
                             @RequestParam("stuUuid[]")List<String> stuUuids,
                             @RequestParam("teUuid")String teUuid) throws IOException {
        Student student = (Student) httpSession.getAttribute("student");
        Project project = new Project();
        project.setUuid(UUID.randomUUID().toString());
        project.setName(proName);
        project.setDescription(proDescription);
        project.settUuid(teUuid);
        project.setHeadman(student.getStuUuid());

        //得到文件名并拼接UUID ， 防止文件重名
        StringBuffer docName = new StringBuffer(UUID.randomUUID().toString());
        docName.append("_"+document.getOriginalFilename());

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

        //TODO 添加自己teamer
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
        for (String tostuuuid:stuUuids) {
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
            System.out.println("****----****"+stuInvite);
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

        System.out.println("============"+httpSession.getServletContext().getContextPath());
        System.out.println("666666666666"+document.getOriginalFilename());
        System.out.println("55555555555555555555"+httpSession.getServletContext().getRealPath("static\\uploadFiles\\projectDoc"));

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

        List<Teamer> teamerList = teamerService.findByStatusAndStuUuid(1,student.getStuUuid());
        List<Project> projectList = new ArrayList<>();
        for (Teamer t:teamerList) {
            projectList.add(projectService.findFirstByUuid(t.getProUuid()));
        }

        System.out.println(teamerList);
        System.out.println(projectList.size());
        System.out.println(projectList);
        model.addAttribute("projectList", projectList);
        return "student/projectList";
    }

    @RequestMapping("/projectDetail.do")
    public String projectDetail(Model model,HttpSession httpSession,
                                @RequestParam("proUUID")String proUUID) {
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
    public String toSubmitTask(Model model,@RequestParam("taskUUID")String taskUUID) {

        Task task = taskService.findFirstByUuid(taskUUID);
        model.addAttribute("task", task);
        return "student/submitTask";
    }

    @RequestMapping(value = "/postSolution.do",method = RequestMethod.POST)
    public String postSolution(Model model,HttpSession httpSession,
                               @RequestParam("title")String title,
                               @RequestParam("taskUUID")String taskUUID,
                               @RequestParam("content")String content,
                               @RequestParam("file1")MultipartFile file1) throws IOException {
        Student student = (Student) httpSession.getAttribute("student");
        Solution solution = new Solution();
        solution.setUuid(UUID.randomUUID().toString());
        solution.setTitle(title);
        solution.setContent(content);


        StringBuffer file1Name = new StringBuffer(UUID.randomUUID().toString());
        file1Name.append("_"+file1.getOriginalFilename());
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


//        model.addAttribute("menuSelected1", "projectManage");
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

        return "redirect:/student/projectDetail.do?proUUID="+proUUID;
    }

    @RequestMapping("/inviteList.do")
    public String inviteList(Model model,HttpSession httpSession) {
        model.addAttribute("menuSelected1", "projectManage");
        model.addAttribute("menuSelected2", "invitation");
        Student student = (Student) httpSession.getAttribute("student");
        List<Invite> inviteList = inviteService.findByToUuidAndStatus(student.getStuUuid(), 1);

        System.out.println(inviteList);

        model.addAttribute("inviteList", inviteList);
        return "student/teamInterview";

    }

}
