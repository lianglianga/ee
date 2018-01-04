package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Book;
import com.liangliang.bookmanager.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
