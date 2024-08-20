package com.example.todo_list.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_list.domain.task.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAllByUserIdAndActive(String userid, Boolean active);
}
