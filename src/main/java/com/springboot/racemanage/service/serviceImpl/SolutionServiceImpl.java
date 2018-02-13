package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.SolutionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Solution;
import com.springboot.racemanage.dao.SolutionDao;

@Service
public class SolutionServiceImpl implements SolutionService{

    @Resource
    private SolutionDao solutionDao;

    public int insert(Solution pojo){
        return solutionDao.insert(pojo);
    }

    public int insertSelective(Solution pojo){
        return solutionDao.insertSelective(pojo);
    }

    public int insertList(List<Solution> pojos){
        return solutionDao.insertList(pojos);
    }

    public int update(Solution pojo){
        return solutionDao.update(pojo);
    }
}
