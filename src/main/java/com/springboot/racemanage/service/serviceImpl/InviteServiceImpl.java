package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.InviteService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Invite;
import com.springboot.racemanage.dao.InviteDao;

@Service
public class InviteServiceImpl implements InviteService{

    @Resource
    private InviteDao inviteDao;

    public int insert(Invite pojo){
        return inviteDao.insert(pojo);
    }

    public int insertSelective(Invite pojo){
        return inviteDao.insertSelective(pojo);
    }

    public int insertList(List<Invite> pojos){
        return inviteDao.insertList(pojos);
    }

    public int update(Invite pojo){
        return inviteDao.update(pojo);
    }

    @Override
    public Integer countByTo(String to) {
        return inviteDao.countByTo(to);
    }

    @Override
    public Integer countByToAndStatus(String to, Integer status) {
        return inviteDao.countByToAndStatus(to,status);
    }
}
