package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.HerokuConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EveryDayMailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private final HerokuConfig herokuConfig;
    private final RestTemplate restTemplate;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendInformationEmail() {
        List<Task> tasks = restTemplate.getForObject(herokuConfig.getHerokuGetTasks(), null, Task.class);
        String taskOrTasks;
        if(tasks.size() == 1) { taskOrTasks = " task";
        } else { taskOrTasks = " tasks";
        }
        simpleEmailService.send(
                new Mail(adminConfig.getAdminMail(),
                        null,
                        SUBJECT,
                        "Currently in database you got: " + tasks.size() + taskOrTasks)
        );
    }
}
