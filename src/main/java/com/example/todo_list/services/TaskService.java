package com.example.todo_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_list.domain.task.Task;
import com.example.todo_list.domain.task.TaskDTO;
import com.example.todo_list.domain.user.User;
import com.example.todo_list.repositories.TaskRepository;
import com.example.todo_list.repositories.UserRepository;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(String userid, TaskDTO data) {
        User user = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("user not found"));

        Task newTask = new Task(data.title(), data.description(), user);
        return taskRepository.save(newTask);
    }
}
