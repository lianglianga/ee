package com.liangliang.bookmanager.repository;

import com.liangliang.bookmanager.bean.Right;
import com.liangliang.bookmanager.bean.State;
import com.liangliang.bookmanager.bean.TableMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RightRepository extends JpaRepository<Right, Integer> {
    @Query(value = "SELECT r.*, o.* FROM `right` r, `order` o" +
            " WHERE r.order_id = o.order_id"+
            " ORDER BY :#{#tableMessage.sort}\n" +
            " LIMIT :#{#tableMessage.offset},:#{#tableMessage.limit}",nativeQuery = true)
    public List<Right> getInitRights(@Param("tableMessage") TableMessage tableMessage) throws Exception;

    @Query(value = "SELECT COUNT(*) FROM `right` r, `order` o" +
            " WHERE r.order_id = o.order_id",nativeQuery = true)
    public Integer getInitRightsCount() throws Exception;

}
