package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Book;
import com.liangliang.bookmanager.bean.Order;
import com.liangliang.bookmanager.bean.TableMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

//    public List<Order> searchOrder(TableMessage tableMessage) throws Exception;
//
//    public Integer searchOrderCount(TableMessage tableMessage) throws Exception;
//
//    public List<Order> getOrderAndUserList(TableMessage tableMessage) throws Exception;
//
//    public Integer orderCount(TableMessage tableMessage) throws Exception;
//
//    public List<Order> getOrderByMore(@Param("bookId") int bookId, @Param("status") int status);

}
