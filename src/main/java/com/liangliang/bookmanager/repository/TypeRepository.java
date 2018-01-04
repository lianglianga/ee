package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.State;
import com.liangliang.bookmanager.bean.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
