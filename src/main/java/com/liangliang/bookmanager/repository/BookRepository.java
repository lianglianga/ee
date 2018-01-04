package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Book;
import com.liangliang.bookmanager.bean.TableMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = " SELECT b.*, t.type_name, s.state_name FROM book b, type t, state s\n" +
            "        WHERE :#{#tableMessage.searchName} LIKE :#{#tableMessage.search}" +
            "        AND b.type_id = t.type_id\n" +
            "        AND b.state = s.state_id\n" +
            "        ORDER BY :#{#tableMessage.sort} :#{#tableMessage.order} LIMIT :#{#tableMessage.offset}, :#{#tableMessage.limit}", nativeQuery = true)
        public List<Book> searchBook(@Param("tableMessage") TableMessage tableMessage) throws Exception;

    @Query(value = "SELECT COUNT(*) FROM book b, type t, state s\n" +
            "        WHERE :#{#tableMessage.searchName} LIKE :#{#tableMessage.search}" +
            "        AND b.type_id = t.type_id\n" +
            "        AND b.state = s.state_id", nativeQuery = true)
    public Integer searchBookCount(@Param("tableMessage") TableMessage tableMessage) throws Exception;

    @Query(value = "SELECT b.* FROM book b\n" +
            "        ORDER BY :#{#tableMessage.sort} :#{#tableMessage.order} LIMIT :#{#tableMessage.offset}, :#{#tableMessage.limit}", nativeQuery = true)
    public List<Book> getBookAndUserList(@Param("tableMessage") TableMessage tableMessage) throws Exception;

    @Query(value = "SELECT COUNT(*) FROM book b", nativeQuery = true)
    public Integer bookCount(@Param("tableMessage") TableMessage tableMessage) throws Exception;

}
