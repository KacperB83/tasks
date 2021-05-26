package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    void mapperCardTest() {
        //Given
        TrelloCardDto cardDto = TrelloCardDto.builder()
                .name("tasks")
                .description("tasks for today")
                .pos("null")
                .listId("2")
                .build();
        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        //Then
        assertEquals(card.getName(), cardDto.getName());
        assertEquals(card.getDescription(), cardDto.getDescription());
    }

    @Test
    void mapperListAndBoardTest() {
        //Given
        TrelloList list1 = TrelloList.builder().id("1").name("one").isClosed(false)
                .build();
        TrelloList list2 = TrelloList.builder().id("2").name("two").isClosed(false)
                .build();
        TrelloList list3 = TrelloList.builder().id("3").name("three").isClosed(false)
                .build();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(list1);
        trelloLists.add(list2);
        trelloLists.add(list3);
        TrelloBoard trelloBoard = TrelloBoard.builder().id("1").name("First board").lists(trelloLists)
                .build();
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloListDto> listDto = trelloMapper.mapToListDto(trelloLists);
        List<TrelloBoardDto> boardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals(3, listDto.size());
        assertEquals(1, boardDtoList.size());
    }
}
