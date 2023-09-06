package runge.worldcities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldCities
{

    private double cityLat;
    private double cityLng;
    private String closestCity;
    private CSVParser parser;

    private final HashMap<String, ArrayList<Double>> cities = new HashMap<>();

    public WorldCities() throws IOException {
        File csvData = new File("worldcities.csv");
        parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
    }

    public String getClosestCity(double givenLat, double givenLon)
    {
        double closestDistance = Double.MAX_VALUE;
        double distance = Math.sqrt((givenLat - cityLat) * (givenLat - cityLat) * (givenLon - cityLng) * (givenLon - cityLng));

        for (CSVRecord record : parser)
        {
            if(record.get(2).equals("lat")||record.get(3).equals("lng"))
            {
                cityLat = Double.MAX_VALUE;
                cityLng = Double.MAX_VALUE;
            } else {
                String city = record.get(0);
                cityLat = Double.parseDouble(record.get(2));
                cityLng = Double.parseDouble(record.get(3));
                ArrayList<Double> coordinates = new ArrayList<>();
                coordinates.add(cityLat);
                coordinates.add(cityLng);
                cities.put(city + " ", coordinates); //puts the three values into hashmap
            }
        }

        for (int i = 0; i < cities.size(); i++) {
            String [] cityNames = new String[cities.size()];
            cityNames[i] = cities.keySet().toString();
            if (distance <= closestDistance) {
                closestDistance = distance;
                closestCity = cityNames[i];
            }
        }
        return closestCity;
    }
}
