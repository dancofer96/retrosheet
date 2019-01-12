package com.cofer.projects.retrosheet.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Game game = new Game("20180401", "0", "NYA", "MIN", 3, 5);

    @Test
    public void serializesToJSON() throws Exception {
        //final Game game = new Game("20180401", "0", "NYA", "MIN", 3, 5);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/game.json"), Game.class));

        assertEquals(expected, MAPPER.writeValueAsString(game));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        //final Game game = new Game("20180401", "0", "NYA", "MIN", 3, 5);

        assertEquals(MAPPER.readValue(fixture("fixtures/game.json"), Game.class), game);
    }

    @Test
    public void testGetWinningTeam() {
        assertEquals("MIN", game.getWinningTeam());
    }

    @Test
    public void testGetLosingTeam() {
        assertEquals("NYA", game.getLosingTeam());
    }

    @Test
    public void testGetSeason() {
        assertEquals("2018", game.getSeason());
    }
}