package com.liangliang.bookmanager.bean;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @Column(name = "type_id")
    @GeneratedValue
    private Integer typeId;

    @Column(name = "type_name")
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