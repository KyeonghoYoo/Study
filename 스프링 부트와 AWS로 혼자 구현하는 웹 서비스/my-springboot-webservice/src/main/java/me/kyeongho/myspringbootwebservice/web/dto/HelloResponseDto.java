package me.kyeongho.myspringbootwebservice.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
