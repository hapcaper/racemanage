package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.TermService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Term;
import com.springboot.racemanage.dao.TermDao;

@Service
public class TermServiceImpl implements TermService{

    @Resource
    private TermDao termDao;

    public int insert(Term pojo){
        return termDao.insert(pojo);
    }

    public int insertSelective(Term pojo){
        return termDao.insertSelective(pojo);
    }

    public int insertList(List<Term> pojos){
        return termDao.insertList(pojos);
    }

    public int update(Term pojo){
        return termDao.update(pojo);
    }
}
