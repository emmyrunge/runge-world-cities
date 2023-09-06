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
        double givenLat = 41.8320;
        double givenLon = -87.8172;

        //when
        String closestCity = worldCities.getClosestCity(givenLat, givenLon);

        //then
        assertEquals(closestCity, "Riverside");

    }

}
