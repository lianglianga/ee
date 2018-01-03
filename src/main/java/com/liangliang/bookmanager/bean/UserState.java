package com.liangliang.bookmanager.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DynamicUpdate
public class UserState {
    @Id
    private Integer userStateId;

    private String userStateInfo;

    public UserState(Integer userStateId, String userStateInfo) {
        this.userStateId = userStateId;
        this.userStateInfo = userStateInfo;
    }

    public UserState() {
        super();
    }

    public Integer getUserStateId() {
        return userStateId;
    }

    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }

    public String getUserStateInfo() {
        return userStateInfo;
    }

    public void setUserStateInfo(String userStateInfo) {
        this.userStateInfo = userStateInfo == null ? null : userStateInfo.trim();
    }
}