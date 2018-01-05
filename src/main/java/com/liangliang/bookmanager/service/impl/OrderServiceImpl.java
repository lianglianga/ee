package com.liangliang.bookmanager.service.impl;

import com.liangliang.bookmanager.bean.*;
import com.liangliang.bookmanager.mapper.BookMapper;
import com.liangliang.bookmanager.mapper.OrderMapper;
import com.liangliang.bookmanager.mapper.UserMapper;
import com.liangliang.bookmanager.repository.BookRepository;
import com.liangliang.bookmanager.repository.OrderRepository;
import com.liangliang.bookmanager.repository.UserRepository;
import com.liangliang.bookmanager.service.BookService;
import com.liangliang.bookmanager.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Order> getOrderList() throws Exception {
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = orderMapper.getOrderList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return orderList;
    }

    @Override
    public boolean addOrder(Order order) {
        boolean state = false;
        try {
            Order res = orderRepository.save(order);
            state = res ==null ? false : true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public boolean updateOrder(Order order) {
        boolean state = false;
        try {
            Order upOrder = orderRepository.findOne(order.getOrderId());
            if (order.getBorrowerId()!=null){
                upOrder.setBorrowerId(order.getBorrowerId());
            }else if(order.getBookId()!=null){
                upOrder.setBookId(order.getBookId());
            }else if(order.getStatus()!=null){
                upOrder.setStatus(order.getStatus());
            }else if(order.getUpdateDate()!=null){
                upOrder.setUpdateDate(order.getUpdateDate());
            }else if(order.getCreateDate()!=null){
                upOrder.setCreateDate(order.getCreateDate());
            }else if(order.getReadyTime()!=null){
                upOrder.setReadyDate(order.getReadyTime());
            }
            Order result = orderRepository.saveAndFlush(upOrder);

            state = result==null ? false : true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        boolean state = false;
        try {
            state = orderRepository.deleteByOrderId(orderId) == 1 ? true : false;
            orderRepository.delete(orderId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Order getOrderById(int orderId){
        Order order = new Order();

        try {
//            order = orderMapper.getOrderById(orderId);
            order = orderRepository.findOne(orderId);
            order.setBorrower(userRepository.findOne(order.getBorrowerId()));
            order.setBook(bookRepository.findOne(order.getBookId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    @Transactional
    public TableMessage searchOrder(TableMessageForOrder tableMessage){
        List<Order> orderList = new ArrayList<>();
        //1.判断你昵称和用户组搜索条件是否为空,若为空则返回所有数据
        try {
            //tableMessage.setSearch("%"+tableMessage.getSearch()+"%");
            tableMessage.setUsernameValue("%"+tableMessage.getUsernameValue()+"%");
            //tableMessage.setBookNameValue("%"+tableMessage.getBookNameValue()+"%");
            orderList = orderRepository.searchOrder(tableMessage);
            for (Order order: orderList){
                Integer userId = order.getBorrowerId();
                Integer bookId = order.getBookId();
                order.setBorrower(userRepository.findOne(userId));
                order.setBook(bookRepository.findOne(bookId));
            }
            tableMessage.setRows(orderList);
            Integer total = orderRepository.searchOrderCount();
            tableMessage.setTotal(total);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tableMessage;
    }

    @Override
    public List<Order> getOrderByMore(int bookId, int status) throws Exception {

        List<Order> orderList = orderRepository.getOrderByMore(bookId, status);

        for (Order order:orderList) {
            User user = userRepository.findOne(order.getBorrowerId());
            order.setBorrower(user);
        }
        return orderList;
    }
}
