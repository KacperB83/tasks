package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskController taskController;

    @Test
    void shouldFetchAllTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = List.of(new TaskDto(1L, "test task", "to do"));

        when(taskController.getTasks()).thenReturn(taskDtos);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDtos);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test task")));
    }

    /*@Test
    void shouldFetchTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task", "to do");
        Task task = new Task(1L, "test task", "to do");
        service.saveTask(task);

        when(taskController.getTask(1L)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        String taskId = taskDto.getId().toString();

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTask/"+taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test task")));
    }*/
    /*@Test
    void shouldDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task", "to do");
        Task task = new Task(1L, "test task", "to do");
        service.saveTask(task);

        taskController.deleteTask(1L);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        String taskId = taskDto.getId().toString();

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask/"+taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }
    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test task", "to do");
        TaskDto taskDto1 = new TaskDto(1L, "test task", "done");
        Task task = new Task(1L, "test task", "to do");
        service.saveTask(task);

        when(taskController.getTask(1L)).thenReturn(taskDto);
        when(taskController.updateTask(taskDto)).thenReturn(taskDto1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        String taskId = taskDto.getId().toString();

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask/"+taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("done")));
    }*/
}
