package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.RaceinfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Raceinfo;
import com.springboot.racemanage.dao.RaceinfoDao;

@Service
public class RaceinfoServiceImpl implements RaceinfoService{

    @Resource
    private RaceinfoDao raceinfoDao;

    public int insert(Raceinfo pojo){
        return raceinfoDao.insert(pojo);
    }

    public int insertSelective(Raceinfo pojo){
        return raceinfoDao.insertSelective(pojo);
    }

    public int insertList(List<Raceinfo> pojos){
        return raceinfoDao.insertList(pojos);
    }

    public int update(Raceinfo pojo){
        return raceinfoDao.update(pojo);
    }

    @Override
    public List<Raceinfo> findByStatusAndTerm(Integer status, Integer term) {
        return raceinfoDao.findByStatusAndTerm(status,term);
    }

    @Override
    public Raceinfo findFirstByUuid(String uuid) {
        return raceinfoDao.findFirstByUuid(uuid);
    }

    @Override
    public Raceinfo findById(Integer id) {
        return raceinfoDao.findById(id);
    }

    @Override
    public List<Raceinfo> findByPageNo(Integer pageNo) {
        //根据页数一次查10条数据
        return raceinfoDao.findByPageNo((pageNo-1)*10);
    }
}
