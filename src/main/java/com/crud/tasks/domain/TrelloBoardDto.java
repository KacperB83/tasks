package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
/*@Getter
@AllArgsConstructor
@NoArgsConstructor*/ //@Data zastępuje pozostałe adnotacje
public class TrelloBoardDto {

    private String name;
    private String id;

}
