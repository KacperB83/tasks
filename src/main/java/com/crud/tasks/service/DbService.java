package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor - ta adnotacja tworzy konstruktor na podstawie pół oznaczonych jako final
@Service
public class DbService {
    @Autowired
    private  TaskRepository repository;

    public DbService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    public void deleteTask(final TaskDto taskDto) {
        repository.deleteById(taskDto.getId());
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }
}
