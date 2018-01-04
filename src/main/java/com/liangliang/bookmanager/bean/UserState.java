package com.liangliang.bookmanager.bean;


import javax.persistence.*;

@Entity
@Table(name = "user_state")
public class UserState {

    @Id
    @Column(name = "user_state_id")
    @GeneratedValue
    private Integer userStateId;

    @Column(name = "user_state_info")
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