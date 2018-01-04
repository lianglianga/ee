package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Order;
import com.liangliang.bookmanager.bean.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
