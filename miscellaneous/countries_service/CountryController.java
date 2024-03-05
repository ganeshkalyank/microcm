package countries_service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CountryController implements HttpHandler {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            List<Country> countries = countryService.getAllCountries();
            String response = "";
            for (Country country : countries) {
                response += country.getName() + "\n";
            }

            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } else {
            exchange.sendResponseHeaders(405, 0); // Method Not Allowed
        }
    }

    public static void main(String[] args) throws IOException {
        CountryService countryService = new CountryService();

        HttpServer server = HttpServer.create();
        server.bind(new java.net.InetSocketAddress(8080), 0);
        server.createContext("/api/countries", new CountryController(countryService));
        server.setExecutor(null);
        server.start();
    }
}
