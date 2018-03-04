package com.springboot.racemanage.service.serviceImpl;

import com.springboot.racemanage.service.MessageService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.springboot.racemanage.po.Message;
import com.springboot.racemanage.dao.MessageDao;

@Service
public class MessageServiceImpl implements MessageService{

    @Resource
    private MessageDao messageDao;

    public int insert(Message pojo){
        return messageDao.insert(pojo);
    }

    public int insertSelective(Message pojo){
        return messageDao.insertSelective(pojo);
    }

    public int insertList(List<Message> pojos){
        return messageDao.insertList(pojos);
    }

    public int update(Message pojo){
        return messageDao.update(pojo);
    }

    @Override
    public Integer countByToAndStatus(String to, Integer status) {
        return messageDao.countByToAndStatus(to,status);
    }
}
