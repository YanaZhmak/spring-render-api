package com.example.spring_render.repository;

import com.example.spring_render.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yana.zhmak on 19.12.2024.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
