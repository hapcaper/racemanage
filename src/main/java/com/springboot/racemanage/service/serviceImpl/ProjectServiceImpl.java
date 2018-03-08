package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.ProjectService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Project;
import com.springboot.racemanage.dao.ProjectDao;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Resource
    private ProjectDao projectDao;

    public int insert(Project pojo){
        return projectDao.insert(pojo);
    }

    public int insertSelective(Project pojo){
        return projectDao.insertSelective(pojo);
    }

    public int insertList(List<Project> pojos){
        return projectDao.insertList(pojos);
    }

    public int update(Project pojo){
        return projectDao.update(pojo);
    }

    @Override
    public List<Project> findByUuid(String uuid) {
        return projectDao.findByUuid(uuid);
    }

    @Override
    public Project findFirstByUuid(String uuid) {
        return projectDao.findFirstByUuid(uuid);
    }
}
