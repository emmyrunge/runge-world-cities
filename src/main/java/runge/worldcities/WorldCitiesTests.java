package runge.worldcities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static junit.framework.TestCase.*;

public class WorldCitiesTests
{
    @Test
    public void getClosestCity() throws IOException {
        //given
        WorldCities worldCities = new WorldCities();
        double givenLat = 40.7128;
        double givenLon = -74.0060;

        //when
        String closestCity = worldCities.getClosestCity(givenLat, givenLon);

        //then
        assertEquals(closestCity, "New York");

    }

    @Test
    public void CSVParser() throws IOException {
        //given
        File csvData = new File("worldcities.csv");
        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);

        //when
        String city = parser.getRecords().get(1).get(0);

        //then
        assertEquals("Tokyo", city);

    }

}
