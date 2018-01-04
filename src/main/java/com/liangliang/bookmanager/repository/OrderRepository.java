package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Query(value = "SELECT * FROM `order` o\n" +
            " WHERE `borrow_id` = :#{#tableMessage.userIdValue}" +
            " LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}",nativeQuery = true)
    List<Order> searchOrder(@Param("tableMessage") TableMessageForOrder tableMessage);

    @Query(value = "SELECT COUNT(*) FROM (SELECT * FROM `order` o\n" +
            " LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}) u",nativeQuery = true)
    int searchOrderCount(@Param("tableMessage") TableMessageForOrder tableMessage);

    @Modifying
    @Transactional
    @Query("delete from Order o where o.orderId = ?1")
    int deleteByOrderId(Integer orderId);
}
