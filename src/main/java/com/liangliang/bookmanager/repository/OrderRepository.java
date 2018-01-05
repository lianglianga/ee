package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT o.*,u.* FROM `order` o,`user` u,`book` b\n" +
            " WHERE o.`borrower_id` = u.`user_id`" +
            "AND o.`book_id` = b.`book_id`" +
            " LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}",nativeQuery = true)
    List<Order> searchOrder(@Param("tableMessage") TableMessageForOrder tableMessage);

    @Query(value = "SELECT COUNT(*) FROM (SELECT * FROM `order` o\n" +
            " LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}) u",nativeQuery = true)
    int searchOrderCount(@Param("tableMessage") TableMessageForOrder tableMessage);

    @Query(value = "SELECT o.*,u.* FROM `order` o,`user` u,`book` b\n" +
            " WHERE o.`borrower_id` = u.`user_id`" +
            "AND o.`book_id` = b.`book_id`" +
            "AND o.`borrower_id` = :#{#tableMessage.userIdValue}" +
            " LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}",nativeQuery = true)
    List<Order> searchOrderByUserId(@Param("tableMessage") TableMessageForOrder tableMessage);

    @Query(value = "SELECT * FROM `order`\n" +
            " `book_id` = :bookId\n" +
            " AND `status` = :status",nativeQuery=true)
    List<Order> getOrderByMore(@Param("bookId") int bookId, @Param("status") int status);

    @Modifying
    @Transactional
    @Query("delete from Order o where o.orderId = ?1")
    int deleteByOrderId(Integer orderId);
}
