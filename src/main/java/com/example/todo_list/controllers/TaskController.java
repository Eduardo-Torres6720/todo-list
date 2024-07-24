package com.example.todo_list.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list.domain.task.Task;
import com.example.todo_list.domain.task.TaskDTO;
import com.example.todo_list.services.TaskService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/user/{userid}")
    public ResponseEntity<Task> postMethodName(@PathVariable String userid, @RequestBody @Valid TaskDTO data) {
        Task newTask = taskService.addTask(userid, data);
        return ResponseEntity.ok(newTask);
    }
    
}
