package runge.worldcities;

import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.nio.charset.Charset;

public class WorldCities {

    private String closestCity;
    private final CSVParser parser;
    public double finalLat;
    public double finalLng;

    public WorldCities() throws IOException {

        File csvData = new File("worldcities.csv");
        parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
    }

    public String getClosestCity(double givenLat, double givenLon) {
        double closestDistance = Double.MAX_VALUE;

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
