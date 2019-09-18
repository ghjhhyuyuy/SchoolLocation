package com.example.ydx.findding_application_test1.entity;

/**
 * Created by ghjhh on 2018/3/4.
 */

public class userPlay {
    private int id;
    private int userId;
    private String state;
    private String location;
    private String playTime;
    private int teamId;

    public userPlay() {
    }

    public userPlay(int id, int userId, String state, String location, String playTime, int teamId) {
        this.id = id;
        this.userId = userId;
        this.state = state;
        this.location = location;
        this.playTime = playTime;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
