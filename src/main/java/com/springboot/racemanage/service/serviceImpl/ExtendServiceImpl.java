package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.ExtendService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Extend;
import com.springboot.racemanage.dao.ExtendDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExtendServiceImpl implements ExtendService{

    @Resource
    private ExtendDao extendDao;

    public int insert(Extend pojo){
        return extendDao.insert(pojo);
    }

    public int insertSelective(Extend pojo){
        return extendDao.insertSelective(pojo);
    }

    public int insertList(List<Extend> pojos){
        return extendDao.insertList(pojos);
    }

}
