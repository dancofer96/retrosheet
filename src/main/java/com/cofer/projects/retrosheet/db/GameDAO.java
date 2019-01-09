package com.cofer.projects.retrosheet.db;

import com.cofer.projects.retrosheet.core.Game;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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

    @SuppressWarnings("unchecked")
    public List<Game> findAll() {
        return list((Query<Game>) namedQuery("com.cofer.projects.retrosheet.core.Game.findAll"));
    }
}

