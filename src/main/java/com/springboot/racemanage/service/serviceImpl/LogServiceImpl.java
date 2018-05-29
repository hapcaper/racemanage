package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.LogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.springboot.racemanage.po.Log;
import com.springboot.racemanage.dao.LogDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService{

    @Resource
    private LogDao logDao;

    public int insert(Log pojo){
        return logDao.insert(pojo);
    }

    public int insertSelective(Log pojo){
        return logDao.insertSelective(pojo);
    }

    public int insertList(List<Log> pojos){
        return logDao.insertList(pojos);
    }

    public int update(Log pojo){
        return logDao.update(pojo);
    }

    @Override
    public List<Log> findByStatusAndProUuid(Integer status, String proUuid) {
        return logDao.findByStatusAndProUuid(status,proUuid);
    }

    @Override
    public List<Map> getLogAndTeamerNameByProUuid(String proUuid) {
        return logDao.getLogAndTeamerNameByProUuid(proUuid);
    }

}
