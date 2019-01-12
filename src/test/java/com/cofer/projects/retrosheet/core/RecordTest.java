package com.cofer.projects.retrosheet.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final String FIXTURE = "fixtures/record.json";

    @Test
    public void serializesToJSON() throws Exception {
        final Record record = new Record("MIN", "2018", 78, 84);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture(FIXTURE), Record.class));

        assertEquals(expected, MAPPER.writeValueAsString(record));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Record record = new Record("MIN", "2018", 78, 84);

        assertEquals(MAPPER.readValue(fixture(FIXTURE), Record.class), record);
    }

    @Test
    public void testCalculateRecordForTeam() {
        List<Game> games = new ArrayList<>();
        games.add(new Game("20180401", "0", "NYA", "MIN", 3, 5));
        games.add(new Game("20180402", "0", "NYA", "MIN", 5, 3));
        games.add(new Game("20170401", "0", "NYA", "MIN", 1, 0));

        assertEquals(new Record("MIN", "2018", 1, 1), Record.calculateRecordForTeam(games, "MIN", "2018"));
    }
}