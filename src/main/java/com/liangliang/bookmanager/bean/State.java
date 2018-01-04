package com.liangliang.bookmanager.bean;


import javax.persistence.*;

@Entity
@Table(name = "state")
public class State {

    @Id
    @Column(name = "state_id")
    @GeneratedValue
    private Integer stateId;

    @Column(name = "state_name")
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