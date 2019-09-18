package com.example.ydx.findding_application_test1.entity;

/**
 * Created by ghjhh on 2018/3/4.
 */

public class UserInfo {
    private int userId;
    private String userName;
    private String sex;
    private int userIdentity;
    private String account;
    private String passWord;
    private int intrgral;

    public UserInfo(int userId, String userName, String sex, int userIdentity, String account, String passWord, int intrgral) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.userIdentity = userIdentity;
        this.account = account;
        this.passWord = passWord;
        this.intrgral = intrgral;
    }

    public UserInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getIntrgral() {
        return intrgral;
    }

    public void setIntrgral(int intrgral) {
        this.intrgral = intrgral;
    }
}
