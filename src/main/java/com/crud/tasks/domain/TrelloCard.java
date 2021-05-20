package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TrelloCard {
    private String name;
    private String description;
    private String pos;
    private String listId;
}
