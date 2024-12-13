package com.example.spring_render.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by yana.zhmak on 09.12.2024.
 */
@Data
public class PostResponseDto {
    private Long id;
    private LocalDateTime date;
    private String title;
    private String text;

}



