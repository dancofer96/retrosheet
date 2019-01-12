package com.cofer.projects.retrosheet.db;

import com.cofer.projects.retrosheet.core.Game;
import com.cofer.projects.retrosheet.core.query.GameQueries;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameDAO extends AbstractDAO<Game> {
    public GameDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Game create(Game game) {
        return persist(game);
    }

    public List<Game> createAll(List<Game> games) {
        List<Game> persistedGames = new ArrayList<>();
        games.forEach(game -> persistedGames.add(this.create(game)));
        return persistedGames;
    }

    @SuppressWarnings("unchecked")
    public List<Game> findAll() {
        return list((Query<Game>) namedQuery(GameQueries.FIND_ALL));
    }

    @SuppressWarnings("unchecked")
    public List<Game> findAllByHomeTeam(String team) {
        return list(
                (Query<Game>) namedQuery(GameQueries.FIND_ALL_BY_HOME_TEAM)
                .setParameter(GameQueries.TEAM, team)
        );
    }

    @SuppressWarnings("unchecked")
    public List<Game> findAllByAwayTeam(String team) {
        return list(
                (Query<Game>) namedQuery(GameQueries.FIND_ALL_BY_AWAY_TEAM)
                        .setParameter(GameQueries.TEAM, team)
        );
    }

    @SuppressWarnings("unchecked")
    public List<Game> findAllByTeam(String team) {
        return list(
                (Query<Game>) namedQuery(GameQueries.FIND_ALL_BY_TEAM)
                        .setParameter(GameQueries.TEAM, team)
        );
    }
}

