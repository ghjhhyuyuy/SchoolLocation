package com.example.ydx.findding_application_test1.entity;

/**
 * Created by ghjhh on 2018/2/25.
 */

public class team {
    private int teamId;
    private String teamName;
    private  int teamMumber;

    public team(int teamId, String teamName, int teamMumber) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamMumber = teamMumber;
    }

    public team() {
    }

    public int getTeamId() { 
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamMumber() {
        return teamMumber;
    }

    public void setTeamMumber(int teamMumber) {
        this.teamMumber = teamMumber;
    }
}
