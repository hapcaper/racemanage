package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.RaceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Race;
import com.springboot.racemanage.dao.RaceDao;

@Service
public class RaceServiceImpl implements RaceService{

    @Resource
    private RaceDao raceDao;

    public int insert(Race pojo){
        return raceDao.insert(pojo);
    }

    public int insertSelective(Race pojo){
        return raceDao.insertSelective(pojo);
    }

    public int insertList(List<Race> pojos){
        return raceDao.insertList(pojos);
    }

    public int update(Race pojo){
        return raceDao.update(pojo);
    }

    @Override
    public List<Race> getStuRaceListByTerm(String stuUuid, Integer term) {
        return raceDao.getStuRaceListByTerm(stuUuid,term);
    }

    @Override
    public Race findByUuid(String uuid) {
        return raceDao.findByUuid(uuid);
    }

    @Override
    public List<Race> getAchivementListByStuUuid(String stuUuid) {
        return raceDao.getByStuUuidAndStatusAndResultNot(stuUuid,1,0);
    }

    @Override
    public List<Race> findByStatusAndTerm(Integer status, Integer term) {
        return raceDao.findByStatusAndTerm(status,term);
    }

    @Override
    public List<Race> findByStatusAndTermAndTUuid(Integer status, Integer term, String tUuid) {
        return raceDao.findByStatusAndTermAndTUuid(status,term,tUuid);
    }

    @Override
    public List<Race> findByStatusAndTUuidAndProgress(Integer status, String tUuid, Integer progress) {
        return raceDao.findByStatusAndTUuidAndProgress(status,tUuid,progress);
    }
}
