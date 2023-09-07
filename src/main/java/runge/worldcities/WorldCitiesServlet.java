package runge.worldcities;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WorldCitiesServlet extends HttpServlet
{
    private final WorldCities ledger;
    private final Gson gson;
    //default constructor
    //pass these into parameters

    public WorldCitiesServlet() throws IOException {
        this(new WorldCities(), new Gson());
    }

    public WorldCitiesServlet(WorldCities ledger, Gson gson) throws IOException {
        this.ledger = ledger;
        this.gson = gson;
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        String lat = req.getParameter("lat");
        String lng = req.getParameter("lon");
        double latit = Double.parseDouble(lat);
        double longi = Double.parseDouble(lng);
        String closestCity = ledger.getClosestCity(latit, longi);

        CityResponse cityResponse = new CityResponse(closestCity, ledger.finalLat, ledger.finalLng);
        resp.setContentType("text/json");
        resp.getWriter().println(gson.toJson(cityResponse));
    }
}
