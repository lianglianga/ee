package com.liangliang.bookmanager.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DynamicUpdate
public class Type {
    @Id
    private Integer typeId;

    private String typeName;

    public Type(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Type() {
        super();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}