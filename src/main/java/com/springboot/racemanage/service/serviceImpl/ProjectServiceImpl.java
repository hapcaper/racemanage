package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.ProjectService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Project;
import com.springboot.racemanage.dao.ProjectDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    @Override
    public List<Project> findByStatusAndUuidIn(Integer status, List<String> uuidList) {
        return projectDao.findByStatusAndUuidIn(status, uuidList);
    }

    @Override
    public List<Project> getProjectForRaceinfoDetail(String stuUuid, String raceinfoUuid) {
        return projectDao.getProjectForRaceinfoDetail(stuUuid,raceinfoUuid);
    }

    @Override
    public Integer countByStatus(Integer status) {
        return projectDao.countByStatus(status);
    }

    @Override
    public List<Project> findByStatus(Integer status) {
        return projectDao.findByStatus(status);
    }

    @Override
    public Project findById(Integer id) {
        return projectDao.findById(id);
    }

    @Override
    public List<Project> findByStatusAndTUuid(Integer status, String tUuid) {
        return projectDao.findByStatusAndTUuid(status,tUuid);
    }

    /**
     * 教师查询自己的可以参加比赛的项目
     * @param tUUID 教师的tUUID
     * @param term 当前期
     * @param raceInfoUUID 要报名的赛事UUID
     * @return 教师可以报名该赛事的项目列表
     */
    @Override
    public List<Project> findCanRaceProject(String tUUID, Integer term, String raceInfoUUID) {
        return projectDao.findCanRaceProject(tUUID,term,raceInfoUUID);
    }
}
