package com.project3.todolist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDto {
    private String contents;
    private Integer isCompleted = 0;
}
