package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo updateTodo(Long id, Todo todo) {
        Optional<Todo> existingTodoOptional = todoRepository.findById(id);
        if (existingTodoOptional.isPresent()) {
            Todo existingTodo = existingTodoOptional.get();
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setCompleted(todo.isCompleted());
            existingTodo.setDueDate(todo.getDueDate());
            existingTodo.setPriority(todo.getPriority());
            return todoRepository.save(existingTodo);
        } else {
            throw new RuntimeException("Todo not found with id: " + id);
        }
    }
}
