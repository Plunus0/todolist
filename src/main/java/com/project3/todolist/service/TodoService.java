package com.project3.todolist.service;

import com.project3.todolist.dto.TodoRequestDto;
import com.project3.todolist.dto.TodoResponseDto;
import com.project3.todolist.entity.Todo;
import com.project3.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        // 기존 데이터 가져오기
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
    
        // 내용 업데이트
        String contents = requestDto.getContents() != null ? requestDto.getContents() : todo.getContents();
    
        // 완료 여부 업데이트: null이 아닐 경우에만 요청 값을 사용
        Integer isCompleted = (requestDto.getIsCompleted() != null) ? requestDto.getIsCompleted() : todo.getIsCompleted();
    
        // 업데이트된 데이터 생성
        Todo updatedTodo = Todo.builder()
                .id(todo.getId())
                .contents(contents)
                .createdDate(todo.getCreatedDate())
                .isCompleted(isCompleted) // 수정된 값 또는 기존 값 설정
                .build();
    
        // 데이터 저장
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

    //최근 10건씩 페이징처리해서서 조회
    public Page<TodoResponseDto> getPagedTodos(int page, int size) {
    PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate")); // 페이지와 크기를 지정
    return todoRepository.findAll(pageable)
            .map(this::toResponseDto); // Page<Entity> -> Page<Dto>
    }
}
