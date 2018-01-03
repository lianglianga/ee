package com.liangliang.bookmanager.bean;

/**
 * Created by YannYao on 2017/12/10.
 */
public class TableMessageForUser extends TableMessage{
    private static final String GROUP = "group";  //用户组
    private Integer groupValue;

    public Integer getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(Integer groupValue) {
        this.groupValue = groupValue;
    }
}
