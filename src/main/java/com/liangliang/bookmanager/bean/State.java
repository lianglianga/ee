package com.liangliang.bookmanager.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DynamicUpdate
public class State {
    @Id
    private Integer stateId;

    private String stateName;

    public State(Integer stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }

    public State() {
        super();
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName == null ? null : stateName.trim();
    }
}