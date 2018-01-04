package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Book;
import com.liangliang.bookmanager.bean.TableMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = " SELECT b.*, t.type_name, s.state_name FROM book b, `type` t, state s" +
            "        WHERE book_name LIKE :#{#tableMessage.search}" +
            "        AND b.type_id = t.type_id" +
            "        AND b.state = s.state_id" +
            "        ORDER BY :#{#tableMessage.sort} " +
            "LIMIT :#{#tableMessage.offset}, :#{#tableMessage.limit}", nativeQuery = true)
    public List<Book> searchBook(@Param("tableMessage") TableMessage tableMessage) throws Exception;

    @Query(value = "SELECT COUNT(*) FROM book b, `type` t, state s" +
            "        WHERE book_name LIKE :#{#tableMessage.search}" +
            "        AND b.type_id = t.type_id" +
            "        AND b.state = s.state_id", nativeQuery = true)
    public int searchBookCount(@Param("tableMessage") TableMessage tableMessage) throws Exception;

    @Query(value = "SELECT * FROM book " +
            "        ORDER BY :#{#tableMessage.sort} " +
            "LIMIT :#{#tableMessage.offset}, :#{#tableMessage.limit}", nativeQuery = true)
    public List<Book> getBookAndUserList(@Param("tableMessage") TableMessage tableMessage) throws Exception;

    @Query(value = "SELECT COUNT(*) FROM book ", nativeQuery = true)
    public int bookCount() throws Exception;

}