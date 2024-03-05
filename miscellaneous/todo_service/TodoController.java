package todo_service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class TodoController implements HttpHandler {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        switch (path) {
            case "/api/todos":
                handleTodosRequest(exchange, method);
                break;
            default:
                exchange.sendResponseHeaders(404, 0); // Not Found
        }
    }

    private void handleTodosRequest(HttpExchange exchange, String method) throws IOException {
        if ("GET".equals(method)) {
            List<Todo> todos = todoService.getAllTodos();
            String response = todos.toString();

            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } else if ("POST".equals(method)) {
            // Assuming JSON payload with {"task": "Your task here"}
            String requestBody = Utils.readRequestBody(exchange);
            String task = Utils.extractTaskFromJson(requestBody);

            todoService.addTodo(task);

            exchange.sendResponseHeaders(201, 0); // Created
        } else if ("PUT".equals(method)) {
            // Assuming JSON payload with {"index": 0}
            String requestBody = Utils.readRequestBody(exchange);
            int index = Utils.extractIndexFromJson(requestBody);

            todoService.markTodoAsCompleted(index);

            exchange.sendResponseHeaders(204, 0); // No Content
        } else {
            exchange.sendResponseHeaders(405, 0); // Method Not Allowed
        }
    }

    public static void main(String[] args) throws IOException {
        TodoService todoService = new TodoService();

        HttpServer server = HttpServer.create();
        server.bind(new java.net.InetSocketAddress(8080), 0);
        server.createContext("/api/todos", new TodoController(todoService));
        server.setExecutor(null);
        server.start();
    }
}
