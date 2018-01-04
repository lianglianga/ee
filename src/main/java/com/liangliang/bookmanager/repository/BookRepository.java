package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT b.*, t.type_name, s.state_name FROM book b, type t, state s" +
            "        WHERE :searchName LIKE :search " +
            "        AND b.type_id = t.type_id" +
            "        AND b.state = s.state_id order by ?#{#pageable}", nativeQuery = true)
        public Page<Book> searchBook(@Param("searchName") String searchName, @Param("search") String search, Pageable pageable) throws Exception;

    @Query(value = "SELECT COUNT(b.book_id) FROM book b, type t, state s" +
            "        WHERE :searchName LIKE :search" +
            "        AND b.type_id = t.type_id" +
            "        AND b.state = s.state_id ", nativeQuery = true)
    public Integer searchBookCount(@Param("searchName") String searchName, @Param("search") String search) throws Exception;

    @Query(value = " SELECT * FROM book b order by ?#{#pageable}", nativeQuery = true)
    public Page<Book> getBookAndUserList(Pageable pageable) throws Exception;

    @Query(value = " SELECT * FROM book b", nativeQuery = true)
    public Integer bookCount() throws Exception;

}
