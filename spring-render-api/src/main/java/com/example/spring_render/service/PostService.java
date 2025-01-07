package com.example.spring_render.service;

import com.example.spring_render.dto.PostRequestDto;
import com.example.spring_render.model.Image;
import com.example.spring_render.model.Post;
import com.example.spring_render.repository.ImageRepository;
import com.example.spring_render.repository.PostRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yana.zhmak on 20.12.2024.
 */

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageHosting imageHosting;

    public Post savePost(PostRequestDto requestDto, MultipartFile[] images) {
        validate(requestDto);

        Post post = new Post();
        post.setDate(LocalDateTime.now());
        post.setTitle(requestDto.getTitle());
        post.setText(requestDto.getText());
        post = postRepository.save(post);

        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file: images) {
            try {
                String imageUrl = imageHosting.save(file.getInputStream());
                Image image = new Image();
                image.setUrl(imageUrl);
                image.setUploadedTime(LocalDateTime.now());
                image.setPost(post);
                imageList.add(image);
                imageRepository.save(image);
            } catch (Exception e) {
                System.err.println("Error saving file");
                //return;
            }
        }

        post.setImages(imageList);
        return post;
    }

    public void validate(PostRequestDto requestDto) {
        if (requestDto.getTitle().isEmpty()) {
            throw new ValidationException("Title is empty");
        }
        if (requestDto.getText().isEmpty()) {
            throw new ValidationException("Post is empty");
        }
        if (requestDto.getText().length() > 5000) {
            throw new ValidationException("Your post length exceed 5000 characters");
        }
    }

}
