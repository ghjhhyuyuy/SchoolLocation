package com.example.ydx.findding_application_test1.entity;

/**
 * Created by ghjhh on 2018/3/4.
 */

public class game {
    private int roomId;
    private int teamId;
    private String time;
    private String centerOfCircle;
    private float r;

    public game() {
    }

    public game(int roomId, int teamId, String time, String centerOfCircle, int r) {
        this.roomId = roomId;
        this.teamId = teamId;
        this.time = time;
        this.centerOfCircle = centerOfCircle;
        this.r = r;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCenterOfCircle() {
        return centerOfCircle;
    }

    public void setCenterOfCircle(String centerOfCircle) {
        this.centerOfCircle = centerOfCircle;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}
