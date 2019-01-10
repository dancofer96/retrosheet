package com.cofer.projects.retrosheet.core;

public class Record {
    private String team;

    private int wins;

    private int losses;

    public Record(String team) {
        this.team = team;
        this.wins = 0;
        this.losses = 0;
    }

    public Record(String team, int wins, int losses) {
        this.team = team;
        this.wins = wins;
        this.losses = losses;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }
}
