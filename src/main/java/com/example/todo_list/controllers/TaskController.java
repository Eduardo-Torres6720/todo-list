package com.example.todo_list.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_list.domain.task.Task;
import com.example.todo_list.domain.task.TaskActiveDTO;
import com.example.todo_list.domain.task.TaskDTO;
import com.example.todo_list.services.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    @PutMapping("/completeTask/{taskid}")
    @Transactional
    public ResponseEntity<String> completeTask(@PathVariable String taskid) {
        try {
            taskService.completedTask(taskid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("not found", e);
        }

    }

    @DeleteMapping("/delete/{taskid}")
    @Transactional
    public ResponseEntity<String> deleteTask(@PathVariable String taskid) {
        try {
            taskService.deleteTask(taskid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("not found", e); 
        }
    }

    @GetMapping("/deletedTask/user/{userid}")
    public ResponseEntity<List<Task>> getDeletedTasks(@PathVariable String userid) {
        List<Task> deletedTask = taskService.getDeletedTasks(userid);

        return ResponseEntity.ok().body(deletedTask);
    }

    @PutMapping("/activeTask")
    @Transactional
    public ResponseEntity<String> TaskRescue(@RequestBody List<TaskActiveDTO> id) {
        for (int i = 0; i < id.size(); i++) {
            taskService.taskRescue(id.get(i).id());
        }

        return ResponseEntity.ok().build();
    }
}
