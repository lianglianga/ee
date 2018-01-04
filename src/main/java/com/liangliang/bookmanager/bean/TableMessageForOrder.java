package com.liangliang.bookmanager.bean;

/**
 * Created by YannYao on 2017/12/10.
 */
public class TableMessageForOrder extends TableMessage{

    private static final String USERNAME = "username";  //用户名
    private String usernameValue;
    private static final String USERID = "user_id";  //用户名
    private String userIdValue;

    private static final String BOOKNAME = "book_name";  //书籍名
    private String bookNameValue;

    public String getUsernameValue() {
        return usernameValue;
    }

    public void setUsernameValue(String usernameValue) {
        this.usernameValue = usernameValue;
    }

    public String getBookNameValue() {
        return bookNameValue;
    }

    public void setBookNameValue(String bookNameValue) {
        this.bookNameValue = bookNameValue;
    }

    public String getUserIdValue() {
        return userIdValue;
    }

    public void setUserIdValue(String userIdValue) {
        this.userIdValue = userIdValue;
    }
}
