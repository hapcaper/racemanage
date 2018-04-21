package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.StudentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Student;
import com.springboot.racemanage.dao.StudentDao;

@Service
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentDao studentDao;

    public int insert(Student pojo){
        return studentDao.insert(pojo);
    }

    public int insertSelective(Student pojo){
        return studentDao.insertSelective(pojo);
    }

    public int insertList(List<Student> pojos){
        return studentDao.insertList(pojos);
    }

    public int update(Student pojo){
        return studentDao.update(pojo);
    }

    @Override
    public Student findFirstByStuNumberAndStuPasswordAndStuStatus(String stunumber, String password, Integer status) {
        Student student = studentDao.findFirstByStuNumberAndStuPasswordAndStuStatus(stunumber, password, status);
        return student;
    }

    @Override
    public List<Student> findStuUuidAndStuName() {
        return studentDao.findStuUuidAndStuName();
    }

    @Override
    public List<Student> findStuUuidAndStuNameByStuUuidNot(String notStuUuid) {
        return studentDao.findStuUuidAndStuNameByStuUuidNot(notStuUuid);
    }

    @Override
    public Integer countByStuStatus(Integer stuStatus) {
        return studentDao.countByStuStatus(stuStatus);
    }

    @Override
    public List<Student> findByStuStatus(Integer stuStatus) {
        return studentDao.findByStuStatus(1);
    }
}
