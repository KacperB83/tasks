package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository repository;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldFetchAllTasks() throws Exception {
        //Given
       List<Task> tasks = List.of(new Task(1L, "test task", "to do"),
        new Task(2L, "test task 2", "to do"));
       List<TaskDto> tasksDto = List.of(new TaskDto(1L, "test task", "to do"),
        new TaskDto(2L, "test task 2", "to do"));

       when(service.getAllTasks()).thenReturn(tasks);
       when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task", "to do");
        TaskDto taskDto = new TaskDto(1L, "test task", "to do");

        when(repository.findById(task.getId())).thenReturn(Optional.of(task));
        when(service.getTask(task.getId())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Long taskId = task.getId();

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTask/")
                        .queryParam("taskId", String.valueOf(taskId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> equals(taskDto))
                .andDo(print())
                .andExpect(ResultMatcher.matchAll());
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task", "to do");
        Task task = new Task(1L, "test task", "to do");

        when(service.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        String taskId = taskDto.getId().toString();

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask/")
                        .queryParam("taskId", taskId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(ResultMatcher.matchAll());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task", "to do");
        TaskDto taskDto1 = new TaskDto(1L, "test task updated", "done");
        Task task = new Task(1L, "test task", "to do");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))

                .andExpect(result -> equals(taskDto1))
                .andDo(print())
                .andExpect(ResultMatcher.matchAll());
    }
    @Test
    void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task", "to do");
        Task task = new Task(1L, "test task", "to do");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(result -> result.equals(taskDto))
                .andDo(print());
    }
}
