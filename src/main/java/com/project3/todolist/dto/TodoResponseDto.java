package com.project3.todolist.dto;

import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createdDate;
    private int isCompleted;
}
