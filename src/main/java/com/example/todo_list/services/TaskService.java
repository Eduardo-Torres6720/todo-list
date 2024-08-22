package com.example.todo_list.services;

import java.util.List;

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

    public List<Task> showTasksForUserid(String userid) {
        List<Task> userTasks = taskRepository.findAllByUserIdAndActive(userid, true);

        return userTasks;
    }

    public Task updateTask(String id, TaskDTO data) {
        Task updatedTask = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("task not found"));

        if (data.description() != null) {
            updatedTask.setDescription(data.description());
        }
        updatedTask.setTitle(data.title());

        return updatedTask;
    }

    public void completedTask(String id) {
        Task taskCompleted = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("task not found"));

        taskCompleted.setCompleted(!taskCompleted.getCompleted());
    }

    public void deleteTask(String id) {
        Task taskDeleted = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("task not found"));

        taskDeleted.setActive(!taskDeleted.getActive());
    }

    public List<Task> getDeletedTasks(String userId) {
        List<Task> deletedTasks = taskRepository.findAllByUserIdAndActive(userId, false);

        return deletedTasks;
    }
}
