package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.TableMessage;
import com.liangliang.bookmanager.bean.TableMessageForUser;
import com.liangliang.bookmanager.bean.User;
import org.springframework.data.domain.PageRequest;
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

    @Query(value = "SELECT u FROM User u JOIN u.userGroup g WHERE u.group=g.userGroupId\n " +
            "AND u.nickname LIKE :#{#tableMessage.search}\n"+
            "AND u.group LIKE :#{#tableMessage.groupValue}\n")
            //"AND :#{#tableMessage.searchName} LIKE :#{#tableMessage.search}\n")
    List<User> searchUser(@Param("tableMessage") TableMessageForUser tableMessage);

//    @Query("SELECT u,g FROM User u LEFT JOIN Group g ON g.userGroupId =  u.group\n" +
//            " AND u.group = :#{#tableMessage.groupValue}\n" +
//            " AND :#{#tableMessage.searchName} LIKE :#{#tableMessage.search}\n" +
//            " ORDER BY :#{#tableMessage.sort} ")
//    int searchUserCount(@Param("tableMessage") TableMessageForUser tableMessage, PageRequest pageRequest);

}
