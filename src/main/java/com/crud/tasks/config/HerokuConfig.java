package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class HerokuConfig {
    @Value("${heroku.getTask.endpoint}")
    private String herokuGetTasks;
}
