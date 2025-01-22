package com.project3.todolist.config;

import com.project3.todolist.entity.Task;
import com.project3.todolist.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(TaskRepository taskRepository) {
        return args -> {
            taskRepository.save(new Task("Sample Task 1", false));
            taskRepository.save(new Task("Sample Task 2", true));
        };
    }
}
