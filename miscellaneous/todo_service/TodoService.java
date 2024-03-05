package todo_service;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private List<Todo> todos;

    public TodoService() {
        this.todos = new ArrayList<>();
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public void addTodo(String task) {
        todos.add(new Todo(task));
    }

    public void markTodoAsCompleted(int index) {
        if (index >= 0 && index < todos.size()) {
            todos.get(index).setCompleted(true);
        }
    }
}
