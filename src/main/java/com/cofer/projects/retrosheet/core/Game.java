package com.cofer.projects.retrosheet.core;

import com.cofer.projects.retrosheet.core.key.GamePK;
import com.cofer.projects.retrosheet.core.query.GameQueries;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gamelogs")
@NamedQueries(
        {
                @NamedQuery(
                        name = GameQueries.FIND_ALL,
                        query = GameQueries.SELECT
                ),
                @NamedQuery(
                        name = GameQueries.FIND_ALL_BY_HOME_TEAM,
                        query = GameQueries.SELECT
                            + "where g.homeTeam = :"
                            + GameQueries.TEAM
                ),
                @NamedQuery(
                        name = GameQueries.FIND_ALL_BY_AWAY_TEAM,
                        query = GameQueries.SELECT
                                + "where g.visitingTeam = :"
                                + GameQueries.TEAM
                ),
                @NamedQuery(
                        name = GameQueries.FIND_ALL_BY_TEAM,
                        query = GameQueries.SELECT
                                + "where g.visitingTeam = :"
                                + GameQueries.TEAM
                                + " or g.homeTeam = :"
                                + GameQueries.TEAM
                )
        })


@IdClass(GamePK.class)
public class Game {
    @Id
    @Column(name = "game_date", nullable = false)
    private String gameDate;

    @Id
    @Column(name = "game_number", nullable = false)
    private String gameNumber;

    @Id
    @Column(name = "visiting_team", nullable = false)
    private String visitingTeam;

    @Id
    @Column(name = "home_team", nullable = false)
    private String homeTeam;

    @Column(name = "visiting_score", nullable = false)
    private int visitingScore;

    @Column(name = "home_score", nullable = false)
    private int homeScore;

    public Game() {
    }

    public Game(String gameDate, String gameNumber, String visitingTeam, String homeTeam, int visitingScore, int homeScore) {
        this.gameDate = gameDate;
        this.gameNumber = gameNumber;
        this.visitingTeam = visitingTeam;
        this.homeTeam = homeTeam;
        this.visitingScore = visitingScore;
        this.homeScore = homeScore;
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

    public int getVisitingScore() {
        return visitingScore;
    }

    public void setVisitingScore(int visitingScore) {
        this.visitingScore = visitingScore;
    }

    public int getHomeScore() {
        return  homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public String getWinningTeam() {
        if (homeScore > visitingScore) {
            return homeTeam;
        } else if (visitingScore > homeScore) {
            return visitingTeam;
        } else {
            return null;
        }
    }

    public String getLosingTeam() {
        if (homeScore < visitingScore) {
            return homeTeam;
        } else if (visitingScore < homeScore) {
            return visitingTeam;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }

        final Game that = (Game) o;

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
