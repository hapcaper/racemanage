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

    @Override
    public Integer countByStuUuid(String stuUuid) {
        return teamerDao.countByStuUuid(stuUuid);
    }

    @Override
    public List<String> findUuidByStuUuid(String stuUuid) {
        return teamerDao.findUuidByStuUuid(stuUuid);
    }

    @Override
    public List<Teamer> findByUuid(String uuid) {
        return teamerDao.findByUuid(uuid);
    }

    @Override
    public List<Teamer> findByStuUuid(String stuUuid) {
        return teamerDao.findByStuUuid(stuUuid);
    }

    @Override
    public Teamer findFirstByStatusAndStuUuidAndProUuid(Integer status, String stuUuid, String proUuid) {
        return teamerDao.findFirstByStatusAndStuUuidAndProUuid(status,stuUuid,proUuid);
    }

    @Override
    public List<Teamer> findByStatusAndProUuid(Integer status, String proUuid) {
        return teamerDao.findByStatusAndProUuid(status,proUuid);
    }

    @Override
    public Teamer findFirstByUuid(String uuid) {
        return teamerDao.findFirstByUuid(uuid);
    }

    @Override
    public List<Teamer> findByStatusAndStuUuid(Integer status, String stuUuid) {
        return teamerDao.findByStatusAndStuUuid(status,stuUuid);
    }
}
