package runge.worldcities;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class WorldCitiesServletTest
{
    @Test
    public void doGet() throws IOException, ServletException {

        //given
        WorldCities ledger = mock();
        Gson gson = new Gson();
        HttpServletRequest request = mock();
        HttpServletResponse response = mock();

        WorldCitiesServlet servlet = new WorldCitiesServlet(ledger, gson);
        doReturn("Howell").when(ledger).getClosestCity(42.6078, -83.9339);
        doReturn("42.6078").when(request).getParameter("lat");
        doReturn("-83.9339").when(request).getParameter("lon");

        PrintWriter out = mock();
        doReturn(out).when(response).getWriter();

        //when
        servlet.doGet(request, response);

        //then
        verify(response).setContentType("text/json");
        verify(out).println(anyString());

    }
}
