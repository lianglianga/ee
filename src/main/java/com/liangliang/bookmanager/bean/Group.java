package com.liangliang.bookmanager.bean;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userGroupId;
    private String userGroupInfo;

    public Group(Integer userGroupId, String userGroupInfo) {
        this.userGroupId = userGroupId;
        this.userGroupInfo = userGroupInfo;
    }

    public Group() {
        super();
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupInfo() {
        return userGroupInfo;
    }

    public void setUserGroupInfo(String userGroupInfo) {
        this.userGroupInfo = userGroupInfo == null ? null : userGroupInfo.trim();
    }
}