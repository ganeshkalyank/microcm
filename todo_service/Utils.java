package todo_service;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Utils {

    public static String readRequestBody(HttpExchange exchange) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            return reader.lines().collect(Collectors.joining());
        }
    }

    public static String extractTaskFromJson(String json) {
        // Implement JSON parsing based on your requirements
        // For simplicity, assuming JSON format: {"task": "Your task here"}
        return json.split(":")[1].replaceAll("[\"{}]", "").trim();
    }

    public static int extractIndexFromJson(String json) {
        // Implement JSON parsing based on your requirements
        // For simplicity, assuming JSON format: {"index": 0}
        return Integer.parseInt(json.split(":")[1].replaceAll("[\"{}]", "").trim());
    }
}
