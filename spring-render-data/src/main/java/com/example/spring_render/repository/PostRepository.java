package com.example.spring_render.repository;

import com.example.spring_render.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yana.zhmak on 09.12.2024.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
