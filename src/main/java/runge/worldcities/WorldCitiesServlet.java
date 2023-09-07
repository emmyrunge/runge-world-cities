package runge.worldcities;

import com.google.gson.Gson;
import runge.worldcities.CityResponse;
import runge.worldcities.WorldCities;

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
        double dLat = Double.parseDouble(lat);
        double dLng = Double.parseDouble(lng);
        String closestCity = ledger.getClosestCity(dLat, dLng);

        CityResponse cityResponse = new CityResponse(closestCity, ledger.finalLat, ledger.finalLng) ;
        resp.setContentType("text/json");
        resp.getWriter().println(gson.toJson(cityResponse));
    }
}
