package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: YannYao
 * @Description:
 * @Date Created in 14:37 2018/1/2
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
