package com.cofer.projects.retrosheet.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Record {
    @JsonProperty("team")
    private String team;

    @JsonProperty("season")
    private String season;

    @JsonProperty("wins")
    private int wins;

    @JsonProperty("losses")
    private int losses;

    public Record() {
    }

    public Record(String team, String season) {
        this.team = team;
        this.season = season;
        this.wins = 0;
        this.losses = 0;
    }

    public Record(String team, String season, int wins, int losses) {
        this.team = team;
        this.season = season;
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

    private void addWin() {
        wins++;
    }

    private void addLoss() {
        losses++;
    }

    public static Record calculateRecordForTeam(List<Game> games, String team, String season) {
        Record record = new Record(team, season);
        games.forEach(
                game -> {
                    if (game.getSeason().equals(season) && team.equals(game.getWinningTeam())) {
                        record.addWin();
                    } else if (game.getSeason().equals(season) && team.equals(game.getLosingTeam())) {
                        record.addLoss();
                    }
                }
        );
        return record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Record)) {
            return false;
        }

        final Record that = (Record) o;

        return Objects.equals(this.team, that.team) &&
                Objects.equals(this.season, that.season) &&
                Objects.equals(this.wins, that.wins) &&
                Objects.equals(this.losses, that.losses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, season, wins, losses);
    }
}
