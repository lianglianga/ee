package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT o.*, u.`username`, b.`book_name` FROM `user` u , `order` o, `book` b\n" +
            " WHERE o.`borrower_id` = u.`user_id`" +
            " AND b.`book_id` = o.`book_id` " +
            " ORDER BY  :#{#tableMessage.sort} " +
            " LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}",nativeQuery = true)
    List<Order> searchOrder(@Param("tableMessage") TableMessageForOrder tableMessage);

    @Query(value = "SELECT COUNT(*) FROM `user` u , `order` o, `book` b\n" +
            " WHERE o.`borrower_id` = u.`user_id`" +
            " AND b.`book_id` = o.`book_id` ",nativeQuery = true)
    int searchOrderCount();

    @Modifying
    @Transactional
    @Query("delete from Order o where o.orderId = ?1")
    int deleteByOrderId(Integer orderId);

    @Query(value = "SELECT * FROM `order` \n" +
            " WHERE `book_id` = :bookId" +
            " AND `status` = :status "
            ,nativeQuery = true)
    public List<Order> getOrderByMore(@Param("bookId") int bookId, @Param("status") int status);

}
