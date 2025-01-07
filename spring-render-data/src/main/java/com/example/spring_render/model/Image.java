package com.example.spring_render.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by yana.zhmak on 09.12.2024.
 */

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

//    @Column(nullable = false)
//    private String fileName;

    @Column(nullable = false)
    private LocalDateTime uploadedTime;

    @Column(nullable = false)
    private String url;

}
