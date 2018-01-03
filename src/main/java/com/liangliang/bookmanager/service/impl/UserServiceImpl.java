package com.liangliang.bookmanager.service.impl;

import com.liangliang.bookmanager.bean.Book;
import com.liangliang.bookmanager.bean.TableMessage;
import com.liangliang.bookmanager.bean.TableMessageForUser;
import com.liangliang.bookmanager.bean.User;
import com.liangliang.bookmanager.mapper.UserMapper;
import com.liangliang.bookmanager.repository.UserRepository;
import com.liangliang.bookmanager.service.UserService;
import com.liangliang.bookmanager.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {

        List<User> userList = new ArrayList<>();
        try {
            userList = repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        User user = new User();

        try {
            user = repository.findOne(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    @Transactional
    public TableMessage searchUser(TableMessageForUser tableMessage) throws Exception {
        List<User> userList = new ArrayList<>();
        //1.判断你昵称和用户组搜索条件是否为空,若为空则返回所有数据
        try {
            // page size
            PageRequest pageRequest = new PageRequest(tableMessage.getOffset()/tableMessage.getLimit()+1
                                                        ,tableMessage.getLimit());
            tableMessage.setSearch("%"+tableMessage.getSearch()+"%");
            userList = repository.searchUser(tableMessage);
            tableMessage.setRows(userList);
//            Integer total = repository.searchUserCount(tableMessage,pageRequest);
//            tableMessage.setTotal(total);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return tableMessage;
    }

    @Override
    public boolean addUser(User user){
        boolean state = false;
        try {
            if(user.getAvatarImageFile() != null && user.getAvatarImageFile().getSize() > 0) {
                String fileName = FileUtil.save(user.getAvatarImageFile(), FileUtil.WINDOWS_PATH);
                user.setAvatarImage(fileName);
            }
            User result = repository.save(user);
            state = result==null ? false : true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return state;
    }

    @Override
    public boolean updateUser(User user){
        boolean state = false;
        try {
            if(user.getAvatarImageFile() != null && user.getAvatarImageFile().getSize() > 0) {
                String fileName = FileUtil.save(user.getAvatarImageFile(), FileUtil.WINDOWS_PATH);
                user.setAvatarImage(fileName);
            }
            User result = repository.saveAndFlush(user);
            state = result==null ? false : true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return state;
    }

    @Override
    public boolean deleteUser(Integer id) {
        boolean state = false;
        try {
            state = repository.deleteByUserId(id) == 1? true : false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public boolean validate(User user) {
        try {
            User result = userMapper.getByEmailAndPwd(user);
            if(result != null){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Integer userLogin(User user) {

        int userId = 0;
        User user1 = new User();
        try {
            //user1 = userMapper.userLogin(user);
            user1 = repository.userLogin(user.getUsername(),user.getPassword());
            if(user1 == null) {
                return -1;
            }else{
                userId = user1.getUserId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return userId;
    }




}
