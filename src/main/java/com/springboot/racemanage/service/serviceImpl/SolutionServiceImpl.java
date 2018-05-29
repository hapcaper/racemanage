package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.SolutionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Solution;
import com.springboot.racemanage.dao.SolutionDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    @Override
    public Solution findByUuid(String uuid) {
        return solutionDao.findByUuid(uuid);
    }

    @Override
    public Solution findByResultAndStatusAndUuid(Integer result, Integer status, String uuid) {
        return solutionDao.findByResultAndStatusAndUuid(result, status, uuid);
    }

    @Override
    public Solution findByResultAndStatusAndTaskUuid(Integer result, Integer status, String taskUuid) {
        return solutionDao.findByResultAndStatusAndTaskUuid(result, status, taskUuid);
    }
}
