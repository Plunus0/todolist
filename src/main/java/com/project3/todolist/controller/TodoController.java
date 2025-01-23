package com.project3.todolist.controller;

import com.project3.todolist.dto.TodoRequestDto;
import com.project3.todolist.dto.TodoResponseDto;
import com.project3.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    //입력
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto) {
        System.out.println("Received POST request with contents: " + requestDto.getContents());
        return ResponseEntity.ok(todoService.createTodo(requestDto));
    }
    //전체조회
    // @GetMapping
    // public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
    //     return ResponseEntity.ok(todoService.getAllTodos());
    // }
    //페이징처리된 최근 10건 조회
    @GetMapping
    public ResponseEntity<Page<TodoResponseDto>> getPagedTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(todoService.getPagedTodos(page, size));
    }
    //단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        TodoResponseDto responseDto = todoService.getTodoById(id);
        return ResponseEntity.ok(responseDto);
    }
    //수정
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoRequestDto requestDto) {
        return ResponseEntity.ok(todoService.updateTodo(id, requestDto));
    }
    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
    //전체삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTodos() {
        todoService.deleteAllTodos();
        return ResponseEntity.noContent().build();
    }
}
