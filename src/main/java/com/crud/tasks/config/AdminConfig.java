package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.name}")
    private String adminName;
    @Value("${admin.mail}")
    private String adminMail;
}
