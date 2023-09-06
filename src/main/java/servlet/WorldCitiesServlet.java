package servlet;

import com.google.gson.Gson;
import runge.worldcities.WorldCities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WorldCitiesServlet extends HttpServlet {

    private final WorldCities ledger = new WorldCities();
    private final Gson gson = new Gson();

    public WorldCitiesServlet() throws IOException {
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        String lat = req.getParameter("lat");
        double dLat = Double.parseDouble(lat);
        String lng = req.getParameter("lng");
        double dLng = Double.parseDouble(lng);
        String closestCity = ledger.getClosestCity(dLat, dLng);

        CityResponse cityResponse = new CityResponse(closestCity, dLat, dLng);
        resp.setContentType("text/json");
        resp.getWriter().println(gson.toJson(cityResponse));
    }
}
