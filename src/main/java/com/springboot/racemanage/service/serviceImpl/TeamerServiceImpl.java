package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.TeamerService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Teamer;
import com.springboot.racemanage.dao.TeamerDao;

@Service
public class TeamerServiceImpl implements TeamerService{

    @Resource
    private TeamerDao teamerDao;

    public int insert(Teamer pojo){
        return teamerDao.insert(pojo);
    }

    public int insertSelective(Teamer pojo){
        return teamerDao.insertSelective(pojo);
    }

    public int insertList(List<Teamer> pojos){
        return teamerDao.insertList(pojos);
    }

    public int update(Teamer pojo){
        return teamerDao.update(pojo);
    }
}
