package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.TaskService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.springboot.racemanage.po.Task;
import com.springboot.racemanage.dao.TaskDao;

@Service
public class TaskServiceImpl implements TaskService{

    @Resource
    private TaskDao taskDao;

    public int insert(Task pojo){
        return taskDao.insert(pojo);
    }

    public int insertSelective(Task pojo){
        return taskDao.insertSelective(pojo);
    }

    public int insertList(List<Task> pojos){
        return taskDao.insertList(pojos);
    }

    public int update(Task pojo){
        return taskDao.update(pojo);
    }

    @Override
    public Integer countByStatusAndToUuid(Integer status, String toUuid) {
        return taskDao.countByStatusAndToUuid(status,toUuid);
    }

    @Override
    public Integer countByStatusAndToUuidIn(Integer status, List<String> toUuidList) {
        return taskDao.countByStatusAndToUuidIn(status,toUuidList);
    }

    @Override
    public List<Task> findByStatusAndToUuid(Integer status, String toUuid) {
        return findByStatusAndToUuid(status,toUuid);
    }
}
