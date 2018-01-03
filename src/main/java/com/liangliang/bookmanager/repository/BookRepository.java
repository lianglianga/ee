package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: YannYao
 * @Description:
 * @Date Created in 14:37 2018/1/2
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
