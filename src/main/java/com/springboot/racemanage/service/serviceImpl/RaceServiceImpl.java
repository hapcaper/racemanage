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
}
