package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //- ta adnotacja tworzy konstruktor na podstawie pól oznaczonych jako final
public class DbService {
    @Autowired
    private  final TaskRepository repository;

    /*public DbService(TaskRepository repository) {
        this.repository = repository;
    }*/ // konstruktor nie jest potrzebny przez adnotację

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTask(final TaskDto taskDto) {
        repository.deleteById(taskDto.getId());
    }




}
