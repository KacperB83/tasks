package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor - ta adnotacja tworzy konstruktor na podstawie pół oznaczonych jako final
@Service
public class DbService {

    private  TaskRepository repository;

    public DbService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task getTask(long id) {
        return repository.findById(id);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
}
