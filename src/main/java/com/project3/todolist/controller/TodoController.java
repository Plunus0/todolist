package com.project3.todolist.controller;

import com.project3.todolist.dto.TodoRequestDto;
import com.project3.todolist.dto.TodoResponseDto;
import com.project3.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto) {
        System.out.println("Received POST request with contents: " + requestDto.getContents());
        return ResponseEntity.ok(todoService.createTodo(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
    TodoResponseDto responseDto = todoService.getTodoById(id);
    return ResponseEntity.ok(responseDto);
}


    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto requestDto) {
        return ResponseEntity.ok(todoService.updateTodo(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTodos() {
        todoService.deleteAllTodos();
        return ResponseEntity.noContent().build();
    }
}
