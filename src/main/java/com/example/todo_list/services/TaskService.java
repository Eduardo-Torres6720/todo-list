package com.example.todo_list.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_list.domain.task.Task;
import com.example.todo_list.domain.task.TaskDTO;
import com.example.todo_list.domain.user.User;
import com.example.todo_list.exceptions.NumberOfInvalidCharactersException;
import com.example.todo_list.exceptions.TaskNotFoundException;
import com.example.todo_list.exceptions.UserNotFoundException;
import com.example.todo_list.repositories.TaskRepository;
import com.example.todo_list.repositories.UserRepository;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private TaskRepository taskRepository;

    private void checkUser(String userid) {
        Optional<User> user = userRepository.findById(userid); 
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
    }

    public Task addTask(String userid, TaskDTO data) {
        User user = userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException());

        if (data.title().length() > 50 || data.description().length() > 150) {
            throw new NumberOfInvalidCharactersException();
        }
        Task newTask = new Task(data.title(), data.description(), user);
        return taskRepository.save(newTask);
    }

    public List<Task> showTasksForUserid(String userid) {
        checkUser(userid);
        List<Task> userTasks = taskRepository.findAllByUserIdAndActive(userid, true);

        return userTasks;
    }

    public Task updateTask(String id, TaskDTO data) {
        Task updatedTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException());

        if (data.title().length() > 50 || data.description().length() > 150) {
            throw new NumberOfInvalidCharactersException();
        }

        if (data.description() != null) {
            updatedTask.setDescription(data.description());
        }
        updatedTask.setTitle(data.title());

        return updatedTask;
    }

    public void completedTask(String id) {
        Task taskCompleted = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException());

        taskCompleted.setCompleted(!taskCompleted.getCompleted());
    }

    public void deleteTask(String id) {
        Task taskDeleted = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException());
        if (taskDeleted.getActive()) {
            taskDeleted.setActive(!taskDeleted.getActive());
        } else {
            taskRepository.delete(taskDeleted);
        }
    }

    public List<Task> getDeletedTasks(String userId) {
        checkUser(userId);
        List<Task> deletedTasks = taskRepository.findAllByUserIdAndActive(userId, false);

        return deletedTasks;
    }

    public void taskRescue(String taskId) {
        Task activeTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException());

        activeTask.setActive(true);
    }

}
