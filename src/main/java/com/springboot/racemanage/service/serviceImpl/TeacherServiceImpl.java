package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.TeacherService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Teacher;
import com.springboot.racemanage.dao.TeacherDao;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Resource
    private TeacherDao teacherDao;

    public int insert(Teacher pojo){
        return teacherDao.insert(pojo);
    }

    public int insertSelective(Teacher pojo){
        return teacherDao.insertSelective(pojo);
    }

    public int insertList(List<Teacher> pojos){
        return teacherDao.insertList(pojos);
    }

    public int update(Teacher pojo){
        return teacherDao.update(pojo);
    }
}
