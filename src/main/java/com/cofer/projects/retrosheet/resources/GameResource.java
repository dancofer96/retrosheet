package com.cofer.projects.retrosheet.resources;

import com.cofer.projects.retrosheet.core.Game;
import com.cofer.projects.retrosheet.core.Record;
import com.cofer.projects.retrosheet.db.GameDAO;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    private final GameDAO gameDAO;

    public GameResource(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    @GET
    @Path("/all")
    @Timed
    @UnitOfWork
    public List<Game> getAllGames() {
        return gameDAO.findAll();
    }

    @GET
    @Timed
    @Path("/{team}/home")
    @UnitOfWork
    public List<Game> getAllGamesByHomeTeam(
            @PathParam("team") String team
    ) {
        return gameDAO.findAllByHomeTeam(team);
    }

    @GET
    @Timed
    @Path("/{team}/away")
    @UnitOfWork
    public List<Game> getAllGamesByAwayTeam(
            @PathParam("team") String team
    ) {
        return gameDAO.findAllByAwayTeam(team);
    }

    @GET
    @Timed
    @Path("/{team}")
    @UnitOfWork
    public List<Game> getAllGamesByTeam(
            @PathParam("team") String team
    ) {
        return gameDAO.findAllByTeam(team);
    }

    @GET
    @Timed
    @Path("/{team}/record")
    @UnitOfWork
    public Record getRecordByTeam(
            @PathParam("team") String team,
            @QueryParam("season") String season
    ) {
        return Record.calculateRecordForTeam(gameDAO.findAllByTeam(team), team, season);
    }
}
