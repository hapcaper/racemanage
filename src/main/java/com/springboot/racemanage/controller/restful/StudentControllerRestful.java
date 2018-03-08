package com.springboot.racemanage.controller.restful;


import com.springboot.racemanage.po.Invite;
import com.springboot.racemanage.po.Message;
import com.springboot.racemanage.po.Task;
import com.springboot.racemanage.po.Teamer;
import com.springboot.racemanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restful/student")
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
    MessageService messageService;



    @RequestMapping(value = "/newMsgNum",method = RequestMethod.GET)
    public Integer newMsgNum(@RequestParam("stuUUID")String stuUUID) {
        int num = 0;
        num = messageService.countByToUuidAndStatus(stuUUID, 1);
        return num;
    }

    @RequestMapping(value = "/newTaskNum",method = RequestMethod.GET)
    public Integer newTaskNum(@RequestParam("stuUUID") String stuUUID) {
        List<String> toList = teamerService.findUuidByStuUuid(stuUUID);
        Integer num=0;
        for (int i = 0; i < toList.size(); i++) {
            num += taskService.countByStatusAndToUuid(2,toList.get(i));
        }
        System.out.println("FUCK!!!!!");
        return num;
    }

    @RequestMapping(value = "/newInvititionNum",method = RequestMethod.GET)
    public int newInvititionNum(@RequestParam("stuUUID") String stuUUID) {
        Integer num = 0;
        num = inviteService.countByToUuidAndStatus(stuUUID, 1);
        return num;
    }

    @RequestMapping(value = "/newTaskList",method = RequestMethod.GET)
    public List<Task> newTaskList(@RequestParam("stuUUID")String stuUUID) {
        List<Teamer> teamerList = teamerService.findByStuUuid(stuUUID);
        List<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < teamerList.size(); i++) {
            taskList.addAll(taskService.findByStatusAndToUuid(2, taskList.get(i).getUuid()));
        }
        return taskList;
    }

    @RequestMapping(value = "/newMessageList",method = RequestMethod.GET)
    public List<Message> newMessageList(@RequestParam("stuUUID")String stuUUID) {
        List<Message> messageList = messageService.findByToUuidAndStatus(stuUUID, 1);
        return messageList;
    }

    @RequestMapping(value = "/newInviteList",method = RequestMethod.GET)
    public List<Invite> newInviteList(@RequestParam("stuUUID")String stuUUID) {
        List<Invite> inviteList = inviteService.findByToUuidAndStatus(stuUUID, 1);
        return inviteList;
    }


}
