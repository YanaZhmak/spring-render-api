package com.example.spring_render.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by yana.zhmak on 09.12.2024.
 */

@Data
public class PostRequestDto {
    @Schema(hidden = true)
    private LocalDateTime date = LocalDateTime.now();
    private String title;
    private String text;
}
