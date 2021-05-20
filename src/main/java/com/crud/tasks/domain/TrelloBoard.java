package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class TrelloBoard {
    private String id;
    private String name;
    private List<TrelloList> lists;
}
