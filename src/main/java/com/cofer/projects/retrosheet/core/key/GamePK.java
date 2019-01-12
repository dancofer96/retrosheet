package com.cofer.projects.retrosheet.core.key;

import com.cofer.projects.retrosheet.core.Game;

import java.io.Serializable;
import java.util.Objects;

public class GamePK implements Serializable {
    private String gameDate;

    private String gameNumber;

    private String visitingTeam;

    private String homeTeam;

    public GamePK() {
    }

    public GamePK(String gameDate, String gameNumber, String visitingTeam, String homeTeam) {
        this.gameDate = gameDate;
        this.gameNumber = gameNumber;
        this.visitingTeam = visitingTeam;
        this.homeTeam = homeTeam;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(String gameNumber) {
        this.gameNumber = gameNumber;
    }

    public String getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(String visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }

        final GamePK that = (GamePK) o;

        return Objects.equals(this.gameDate, that.gameDate) &&
                Objects.equals(this.gameNumber, that.gameNumber) &&
                Objects.equals(this.visitingTeam, that.visitingTeam) &&
                Objects.equals(this.homeTeam, that.homeTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameDate, gameNumber, visitingTeam, homeTeam);
    }
}
