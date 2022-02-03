package com.jiyeon.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseMessage {

    //ResponseMessage is like dto, to transfer datas;

    private String content;
}
