package com.zhanghk.entity;

import lombok.Data;

import java.io.Serializable;

@Data //lombok组件
public class Type implements Serializable {
    private Integer id;
    private String typeName;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
}
