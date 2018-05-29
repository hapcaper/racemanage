package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.LeaderService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Leader;
import com.springboot.racemanage.dao.LeaderDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeaderServiceImpl implements LeaderService{

    @Resource
    private LeaderDao leaderDao;

    public int insert(Leader pojo){
        return leaderDao.insert(pojo);
    }

    public int insertSelective(Leader pojo){
        return leaderDao.insertSelective(pojo);
    }

    public int insertList(List<Leader> pojos){
        return leaderDao.insertList(pojos);
    }

    public int update(Leader pojo){
        return leaderDao.update(pojo);
    }

    @Override
    public Leader findByLStatusAndLPasswordAndLName(Integer lStatus, String lPassword, String lName) {
        return leaderDao.findByLStatusAndLPasswordAndLName(lStatus,lPassword,lName);
    }

    @Override
    public Leader findByLStatusAndLPasswordAndLNumber(Integer lStatus, String lPassword, String lNumber) {
        return leaderDao.findByLStatusAndLPasswordAndLNumber(lStatus,lPassword,lNumber);
    }
}
