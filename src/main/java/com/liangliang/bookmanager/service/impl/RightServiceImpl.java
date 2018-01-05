package com.liangliang.bookmanager.service.impl;

import com.liangliang.bookmanager.bean.Order;
import com.liangliang.bookmanager.bean.Right;
import com.liangliang.bookmanager.bean.TableMessage;
import com.liangliang.bookmanager.bean.User;
import com.liangliang.bookmanager.mapper.OrderMapper;
import com.liangliang.bookmanager.mapper.RightMapper;
import com.liangliang.bookmanager.mapper.UserMapper;
import com.liangliang.bookmanager.repository.OrderRepository;
import com.liangliang.bookmanager.repository.RightRepository;
import com.liangliang.bookmanager.repository.UserRepository;
import com.liangliang.bookmanager.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RightServiceImpl implements RightService {

    @Autowired
    private RightRepository rightRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer addRight(Right right){

//        int state = 0;
        try {
            rightRepository.save(right);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    @Override
    public TableMessage getInitRights(TableMessage tableMessage){

        List<Right> rightList = new ArrayList<>();
        try {
            rightList = rightRepository.getInitRights(tableMessage);
            if(tableMessage.getSearch()!=null){
                if(tableMessage.getSearch().equals("")){
                    rightList = rightRepository.getInitRights(tableMessage);
                    for (Right right : rightList) {
                        Order order = orderRepository.findOne(right.getOrderId());
                        User user = userRepository.findOne(order.getBorrowerId());
                        order.setBorrower(user);
                        right.setOrder(order);
                    }
                    tableMessage.setRows(rightList);
                    tableMessage.setTotal(rightRepository.getInitRightsCount());
                }else {
                    tableMessage.setSearch("%"+tableMessage.getSearch()+"%");
                    List<Right> searchBookList = rightRepository.getInitRights(tableMessage);
                    tableMessage.setRows(searchBookList);
                    for (Right right : searchBookList) {
                        Order order = orderRepository.findOne(right.getOrderId());
                        User user = userRepository.findOne(order.getBorrowerId());
                        order.setBorrower(user);
                        right.setOrder(order);
                    }
                    tableMessage.setTotal(rightRepository.getInitRightsCount());
                }

            }else {
                tableMessage.setRows(rightList);
                tableMessage.setTotal(rightRepository.getInitRightsCount());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return tableMessage;
    }

    @Override
    public Integer deleteRight(int rightId){

//        int state = 0;
        try {
            rightRepository.delete(rightId);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    @Override
    public Integer updateRight(Right right){

//        int state = 0;
        try {
            Right upRight = rightRepository.findOne(right.getRightId());
            upRight.setRightStateId(right.getRightStateId());
            rightRepository.saveAndFlush(upRight);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    @Override
    public Right getRightInfoById(int rightId){

        Right right = new Right();

        try {
            right = rightRepository.findOne(rightId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return right;
    }
}
