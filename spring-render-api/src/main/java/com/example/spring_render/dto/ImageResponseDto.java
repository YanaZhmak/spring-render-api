package com.example.spring_render.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by yana.zhmak on 23.12.2024.
 */

@Data
public class ImageResponseDto {
    private Long id;
    private LocalDateTime uploadedTime;
    private String url;
}
