package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.Teamer_raceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Teamer_race;
import com.springboot.racemanage.dao.Teamer_raceDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Teamer_raceServiceImpl implements Teamer_raceService{

    @Resource
    private Teamer_raceDao teamer_raceDao;

    public int insert(Teamer_race pojo){
        return teamer_raceDao.insert(pojo);
    }

    public int insertSelective(Teamer_race pojo){
        return teamer_raceDao.insertSelective(pojo);
    }

    public int insertList(List<Teamer_race> pojos){
        return teamer_raceDao.insertList(pojos);
    }

    public int update(Teamer_race pojo){
        return teamer_raceDao.update(pojo);
    }
}
