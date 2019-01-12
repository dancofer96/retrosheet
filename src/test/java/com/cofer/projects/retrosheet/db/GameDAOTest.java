package com.cofer.projects.retrosheet.db;

import com.cofer.projects.retrosheet.core.Game;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
class GameDAOTest {
    private final DAOTestExtension daoTestExtension = DAOTestExtension.newBuilder().addEntityClass(Game.class).build();
    private GameDAO gameDAO;
    private static List<Game> expectedGames = new ArrayList<>();

    @BeforeAll
    public static void setUpAll() throws Exception {
        expectedGames.add(new Game("20180401", "0", "NYA", "MIN", 3, 5));
        expectedGames.add(new Game("20180402", "0", "MIN", "NYA", 5, 3));
        expectedGames.add(new Game("20180403", "0", "NYN", "NYA", 1, 0));
    }

    @BeforeEach
    public void setUp() throws Exception {
        gameDAO = new GameDAO(daoTestExtension.getSessionFactory());
    }

    @Test
    public void testCreate() {
        Game actualGame = daoTestExtension.inTransaction(() -> gameDAO.create(expectedGames.get(0)));
        assertEquals(expectedGames.get(0), actualGame);
    }

    @Test
    public void testCreateAll() {
        List<Game> actualGames = daoTestExtension.inTransaction(() -> gameDAO.createAll(expectedGames));
        assertEquals(expectedGames.size(), actualGames.size());
        assertTrue(expectedGames.contains(actualGames.get(0)));
        assertTrue(expectedGames.contains(actualGames.get(1)));
        assertTrue(expectedGames.contains(actualGames.get(2)));
    }

    @Test
    public void testFindAll() {
        daoTestExtension.inTransaction(() -> gameDAO.createAll(expectedGames));
        List<Game> actualGames = daoTestExtension.inTransaction((() -> gameDAO.findAll()));
        assertEquals(expectedGames.size(), actualGames.size());
        assertTrue(expectedGames.contains(actualGames.get(0)));
        assertTrue(expectedGames.contains(actualGames.get(1)));
        assertTrue(expectedGames.contains(actualGames.get(2)));
    }

    @Test
    public void testFindAllByHomeTeam() {
        daoTestExtension.inTransaction(() -> gameDAO.createAll(expectedGames));
        List<Game> actualGames = daoTestExtension.inTransaction((() -> gameDAO.findAllByHomeTeam("NYA")));
        assertEquals(2, actualGames.size());
        assertTrue(expectedGames.contains(actualGames.get(0)));
        assertTrue(expectedGames.contains(actualGames.get(1)));
    }

    @Test
    public void testFindAllByAwayTeam() {
        daoTestExtension.inTransaction(() -> gameDAO.createAll(expectedGames));
        List<Game> actualGames = daoTestExtension.inTransaction((() -> gameDAO.findAllByAwayTeam("NYA")));
        assertEquals(1, actualGames.size());
        assertTrue(expectedGames.contains(actualGames.get(0)));
    }

    @Test
    public void testFindAllByTeam() {
        daoTestExtension.inTransaction(() -> gameDAO.createAll(expectedGames));
        List<Game> actualGames = daoTestExtension.inTransaction((() -> gameDAO.findAllByAwayTeam("NYN")));
        assertEquals(1, actualGames.size());
        assertTrue(expectedGames.contains(actualGames.get(0)));
    }
}
