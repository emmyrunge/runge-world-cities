package runge.worldcities;

import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.InputStream;
import java.nio.charset.Charset;

public class WorldCities {

    private String closestCity;
    public double finalLat;
    public double finalLng;

    public WorldCities() {
    }

    public String getClosestCity(double givenLat, double givenLon) throws IOException {
        double closestDistance = Double.MAX_VALUE;

        InputStream inputStream = WorldCities.class.getClassLoader()
                .getResourceAsStream("worldcities.csv");

        CSVParser parser = CSVParser.parse(inputStream, Charset.defaultCharset(), CSVFormat.RFC4180);
        Iterable<CSVRecord> cityParser = parser.getRecords();

        for (CSVRecord record : cityParser) {
            double cityLat;
            double cityLng;
            if (record.get(2).equals("lat") || record.get(3).equals("lng")) {
                cityLat = Double.MAX_VALUE;
                cityLng = Double.MAX_VALUE;
            } else {
                cityLat = Double.parseDouble(record.get(2));
                cityLng = Double.parseDouble(record.get(3));
                double distance =
                        Math.sqrt(((givenLat - cityLat) * (givenLat - cityLat))
                            + ((givenLon - cityLng) * (givenLon - cityLng)));
                if (distance <= closestDistance) {
                    closestDistance = distance;
                    closestCity = record.get(0);
                    finalLat = cityLat;
                    finalLng = cityLng;
                }
            }
        }
        return closestCity;
    }
}
