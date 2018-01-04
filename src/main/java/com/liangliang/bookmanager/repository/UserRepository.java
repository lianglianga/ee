package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.TableMessageForUser;
import com.liangliang.bookmanager.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: YannYao
 * @Description:
 * @Date Created in 14:37 2018/1/2
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("delete from User u where u.userId = ?1")
    int deleteByUserId(Integer userId);

    @Query("SELECT u FROM User u WHERE u.username = ?1  and u.password = ?2")
    User userLogin(String username,String password);

    @Query(value = "SELECT u.*,g.* FROM user u LEFT JOIN `group` g ON u.`group`=g.user_group_id\n" +
            "WHERE u.nickname LIKE :#{#tableMessage.search}\n"+
            "AND u.group LIKE :#{#tableMessage.groupValue}\n" +
            "ORDER BY :#{#tableMessage.sort}\n" +
            "LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}",nativeQuery = true)
    List<User> searchUser(@Param("tableMessage") TableMessageForUser tableMessage);

    @Query(value = "SELECT COUNT(*) FROM (\n" +
            "SELECT u.*,g.* FROM user u LEFT JOIN `group` g ON u.`group`=g.user_group_id\n" +
            "WHERE u.nickname LIKE :#{#tableMessage.search}\n"+
            "AND u.group LIKE :#{#tableMessage.groupValue}\n" +
            "ORDER BY :#{#tableMessage.sort}\n" +
            "LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}) u",nativeQuery = true)
    int searchUserCount(@Param("tableMessage") TableMessageForUser tableMessage);

}
