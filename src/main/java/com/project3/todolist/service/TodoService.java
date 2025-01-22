package com.project3.todolist.service;

import com.project3.todolist.dto.TodoRequestDto;
import com.project3.todolist.dto.TodoResponseDto;
import com.project3.todolist.entity.Todo;
import com.project3.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    //등록
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = Todo.builder()
                .contents(requestDto.getContents())
                .build();
        Todo savedTodo = todoRepository.save(todo);
        return toResponseDto(savedTodo);
    }

    //조회
    public List<TodoResponseDto> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    //단일조회
    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo with id " + id + " not found"));
        return toResponseDto(todo);
    }

    //수정
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        
            String contents = requestDto.getContents() != null ? requestDto.getContents() : todo.getContents();
            int isCompleted = requestDto.getIsCompleted() != 0 ? requestDto.getIsCompleted() : 0;
        
            Todo updatedTodo = Todo.builder()
                    .id(todo.getId())
                    .contents(contents)
                    .createdDate(todo.getCreatedDate())
                    .isCompleted(isCompleted)
                    .build();
    
        updatedTodo = todoRepository.save(updatedTodo);
        return toResponseDto(updatedTodo);
    }

    //
    private TodoResponseDto toResponseDto(Todo todo) {
        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setId(todo.getId());
        responseDto.setContents(todo.getContents());
        responseDto.setCreatedDate(todo.getCreatedDate());
        responseDto.setIsCompleted(todo.getIsCompleted());
        return responseDto;
    }

    //단일삭제
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        todoRepository.delete(todo);
    }

    //전체삭제
    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }
}
