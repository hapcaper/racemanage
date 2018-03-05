package com.springboot.racemanage.controller.restful;


import com.springboot.racemanage.service.InviteService;
import com.springboot.racemanage.service.ProjectService;
import com.springboot.racemanage.service.TaskService;
import com.springboot.racemanage.service.TeamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/newMsgNum",method = RequestMethod.GET)
    public Integer newMsgNum() {

        return 0;
    }

    @RequestMapping(value = "/newTaskNum",method = RequestMethod.GET)
    public Integer newTaskNum(@RequestParam("stuUUID") String stuUUID) {
        List<String> toList = teamerService.findUuidByStuUuid(stuUUID);
        Integer num=0;

        for (int i = 0; i < toList.size(); i++) {
            num += taskService.countByStatusAndToUuid(1,toList.get(i));
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


}
