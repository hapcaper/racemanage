package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.LeaderService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Leader;
import com.springboot.racemanage.dao.LeaderDao;

@Service
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
}
