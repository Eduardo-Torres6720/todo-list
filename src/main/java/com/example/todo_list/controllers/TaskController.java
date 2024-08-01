package com.example.todo_list.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list.domain.task.Task;
import com.example.todo_list.domain.task.TaskDTO;
import com.example.todo_list.services.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/user/{userid}/addTask")
    public ResponseEntity<Task> postTask(@PathVariable String userid, @RequestBody @Valid TaskDTO data) {
        Task newTask = taskService.addTask(userid, data);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable String userid) {
        List<Task> tasks = taskService.showTasksForUserid(userid);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/updateTask/{taskid}")
    @Transactional
    public ResponseEntity<Task> updateTask(@PathVariable String taskid, @RequestBody @Valid TaskDTO data) {
        Task task = taskService.updateTask(taskid, data);
        
        return ResponseEntity.ok(task);
    }
}
