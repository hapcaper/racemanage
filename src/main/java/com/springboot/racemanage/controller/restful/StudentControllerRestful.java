package com.springboot.racemanage.controller.restful;


import com.springboot.racemanage.controller.restful.restfulDO.ResultDO;
import com.springboot.racemanage.dao.RaceDao;
import com.springboot.racemanage.dao.TermDao;
import com.springboot.racemanage.po.*;
import com.springboot.racemanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/restful/student")
@Transactional
public class StudentControllerRestful {

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @Autowired
    TeamerService teamerService;

    @Autowired
    InviteService inviteService;

    @Autowired
    StudentService studentService;

    @Autowired
    private MessageService messageService;
    @Autowired
    private TermService termService;
    @Autowired
    private RaceService raceService;


    @RequestMapping(value = "/newMsgNum",method = RequestMethod.GET)
    public Integer newMsgNum(@RequestParam("stuUUID")String stuUUID) {
        int num;
        num = messageService.countByToUuidAndStatus(stuUUID, 1);
        return num;
    }

    @RequestMapping(value = "/newTaskNum",method = RequestMethod.GET)
    public Integer newTaskNum(@RequestParam("stuUUID") String stuUUID) {
        List<String> toList = teamerService.findUuidByStuUuid(stuUUID);
        Integer num=0;
        for (String aToList : toList) {
            num += taskService.countByStatusAndToUuid(2, aToList);
        }
        return num;
    }

    @RequestMapping(value = "/newInvititionNum",method = RequestMethod.GET)
    public int newInvititionNum(@RequestParam("stuUUID") String stuUUID) {
        return inviteService.countByToUuidAndStatus(stuUUID, 1);
    }

    @RequestMapping(value = "/newTaskList",method = RequestMethod.GET)
    public List<Task> newTaskList(@RequestParam("stuUUID")String stuUUID) {
        List<Teamer> teamerList = teamerService.findByStuUuid(stuUUID);
        List<Task> taskList = new ArrayList<Task>();
        IntStream.range(0, teamerList.size()).forEach(i -> taskList.addAll(taskService.findByStatusAndToUuid(2, taskList.get(i).getUuid())));
        return taskList;
    }

    @RequestMapping(value = "/newMessageList",method = RequestMethod.GET)
    public List<Message> newMessageList(@RequestParam("stuUUID")String stuUUID) {
        return messageService.findByToUuidAndStatus(stuUUID, 1);
    }

    @RequestMapping(value = "/newInviteList",method = RequestMethod.GET)
    public List<Invite> newInviteList(@RequestParam("stuUUID")String stuUUID) {
        return inviteService.findByToUuidAndStatus(stuUUID, 1);
    }

    @RequestMapping(value = "/notCompleteTaskNum",method = RequestMethod.GET)
    public Integer notCompleteTaskNum(@RequestParam("teamerUUID")String teamerUUID) {
        return taskService.countByStatusNotAndToUuidAndProgress(0, teamerUUID, 2);
    }

    @RequestMapping(value = "/completedTaskNum",method = RequestMethod.GET)
    public Integer completedTaskNum(@RequestParam("teamerUUID")String teamerUUID) {
        return taskService.countByStatusNotAndToUuidAndProgress(0, teamerUUID, 1);
    }


    @RequestMapping(value = "/login")
    public ResultDO login(@RequestParam("stuNumber") String stuNumber,
                        @RequestParam("password") String password)  {
        System.out.println(stuNumber);
        System.out.println(password);
        Student student = studentService.findFirstByStuNumberAndStuPasswordAndStuStatus(stuNumber, password, 1);
        System.out.println(student);
        ResultDO resultDO = new ResultDO();
        if (student != null) {
            resultDO.setResult(student);
            resultDO.setCode(1);
        } else {
            resultDO.setCode(0);
        }
        return resultDO;
    }

//    @RequestMapping("/loginTest")
//    public String loginTest(@RequestParam("Number") String stuNumber,
//                            @RequestParam("passWord") String password) {
//        Student student = studentService.findFirstByStuNumberAndStuPasswordAndStuStatus(stuNumber, password, 1);
//        if (student != null) {
//            return "OK";
//        }else {
//            return "Wrong";
//        }
//    }

    @RequestMapping(value = "/getMyProjectList")
    public ResultDO getMyProjectList(@RequestParam("stuUUID") String stuUUID) {

        List<Teamer> teamerList = teamerService.findByStatusAndStuUuid(1,stuUUID);
        List<Project> projectList = new ArrayList<>();
        for (Teamer t:teamerList) {
            projectList.add(projectService.findFirstByUuid(t.getProUuid()));
        }

        ResultDO resultDO = new ResultDO();
        if (teamerList == null) {
            resultDO.setCode(0);
            resultDO.setMsg("可能是stuUUID不正确");
            return resultDO;
        }
        resultDO.setResult(projectList);
        resultDO.setCode(1);
        resultDO.setMsg("正常结果");
        return resultDO;
    }

    @RequestMapping("/getMyRaceList")
    public ResultDO getMyRaceList(@RequestParam("stuUUID") String stuUUID,
                                  //progress   all:所有进度的赛事    complete:已完成的赛事
                                  @RequestParam("progress")String progress) {
        Term term = termService.findFirstByStatusOrderByTerm(1);
        List<Race> myRaceList = null;
        ResultDO resultDO = new ResultDO();
        switch (progress) {
            case "all":
                myRaceList = raceService.getStuRaceListByTerm(stuUUID, term.getTerm());
                break;
            case "complete":
                myRaceList = raceService.getAchivementListByStuUuid(stuUUID);
                break;
            default:
                resultDO.setCode(0);
                resultDO.setMsg("progress字段不合法  progress可选：all,complete");
                break;
        }
        if (myRaceList != null) {
            resultDO.setCode(1);
            resultDO.setMsg("正常结果");
            resultDO.setResult(myRaceList);
        } else {
            resultDO.setCode(0);
            resultDO.setMsg("查询失败");
        }
        return resultDO;
    }

}
