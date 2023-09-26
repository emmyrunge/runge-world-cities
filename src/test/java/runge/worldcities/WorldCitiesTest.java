package runge.worldcities;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldCitiesTest
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
