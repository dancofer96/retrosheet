package com.cofer.projects.retrosheet.core.query;

public class GameQueries {
    public static final String PACKAGE = "com.cofer.projects.retrosheet.core.Game";

    public static final String FIND_ALL = PACKAGE + ".findAll";
    public static final String FIND_ALL_BY_HOME_TEAM = PACKAGE + ".findAllByHomeTeam";
    public static final String FIND_ALL_BY_AWAY_TEAM = PACKAGE + ".findAllByAwayTeam";
    public static final String FIND_ALL_BY_TEAM = PACKAGE + ".findAllByTeam";

    public static final String SELECT = "SELECT g FROM Game g ";

    public static final String TEAM = "team";
}
