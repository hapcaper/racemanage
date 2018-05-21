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

    @Override
    public List<Teacher> findTNameAndTUuid() {
        return teacherDao.findTNameAndTUuid();
    }

    @Override
    public Integer countByTStatus(Integer tStatus) {
        return teacherDao.countByTStatus(tStatus);
    }

    @Override
    public List<Teacher> findByTStatus(Integer tStatus) {
        return teacherDao.findByTStatus(tStatus);
    }

    @Override
    public Teacher login(Integer tStatus, String tNumber, String tPassword) {
        return teacherDao.findByTStatusAndTNumberAndTPassword(tStatus, tNumber, tPassword);
    }

    @Override
    public Teacher findByTUuid(String tUuid) {
        return teacherDao.findByTUuid(tUuid);
    }
}
