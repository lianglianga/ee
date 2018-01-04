package com.liangliang.bookmanager.service.impl;

import com.liangliang.bookmanager.bean.Group;
import com.liangliang.bookmanager.repository.GroupRepository;
import com.liangliang.bookmanager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author: YannYao
 * @Description:
 * @Date Created in 15:20 2018/1/3
 */
@Component
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository repository;
    @Override
    public Group getGroupById(Integer id) throws Exception {
        Group group = new Group();

        try {
            group = repository.findOne(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return group;
    }
}
