package com.example.spring_render.controller;

import com.example.spring_render.dto.PostRequestDto;
import com.example.spring_render.dto.PostResponseDto;
import com.example.spring_render.model.Post;
import com.example.spring_render.repository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yana.zhmak on 09.12.2024.
 */

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    /**
     * Endpoint to return all posts
     *
     * @return The list of PostResponseDto
     */

    @Operation(summary = "Get all posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts received successfully ")
    })
    @GetMapping("/posts")
    public List<PostResponseDto> getAllPosts(@RequestParam(required = false) Pageable pageable) {
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        if (pageable == null || pageable.isUnpaged()) {
            List<Post> posts = postRepository.findAll();
            for (Post post: posts) {
                postResponseDtos.add(createResponseDto(post));
            }
        } else {
            Page<Post> posts = postRepository.findAll(pageable);
            for (Post note : posts) {
                postResponseDtos.add(createResponseDto(note));
            }
        }

        return postResponseDtos;
    }

    /**
     * Endpoint to create a new post
     *
     * @param requestDto of post to be created
     * @return new PostResponseDto object
     */

    @Operation(summary = "Create a new post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseDto.class))
    }),
            @ApiResponse(responseCode = "400", description = "Bad request. See line error.")
    })
    @PostMapping("/createPost")
    public PostResponseDto createNewPost(@RequestBody PostRequestDto requestDto) {
        validate(requestDto);

        Post post = new Post();
        post.setDate(requestDto.getDate());
        post.setTitle(requestDto.getTitle());
        post.setText(requestDto.getText());
        post = postRepository.save(post);

        return createResponseDto(post);
    }

    /**
     * Endpoint to update post by id
     *
     * @param id          of post to be updated
     * @param updatedPost updated PostRequestDto
     * @return The updated PostResponseDto
     */

    @Operation(summary = "Update post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PostResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request. See line error."),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PutMapping("/updatePost")
    public PostResponseDto updatePost(@RequestParam Long id, @RequestBody PostRequestDto updatedPost) {
        Post post = postRepository.findById(id).orElse(null);
        post.setId(id);
        post.setDate(updatedPost.getDate());
        post.setTitle(updatedPost.getTitle());
        post.setText(updatedPost.getText());
        post = postRepository.save(post);

        return createResponseDto(post);
    }

    /**
     * Endpoint to delete post by their id
     *
     * @param id of post to be deleted
     */

    @Operation(summary = "Delete post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/deletePost")
    public void deletePost(@RequestParam Long id) {
        postRepository.deleteById(id);
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

    /**
     * Method to create response Dto
     *
     * @param post to converted to PostResponseDto object
     * @return The new PostResponseDto object
     */
    public PostResponseDto createResponseDto(Post post) {
        PostResponseDto postDto = new PostResponseDto();
        postDto.setId(post.getId());
        postDto.setDate(post.getDate());
        postDto.setTitle(post.getTitle());
        postDto.setText(post.getText());

        return postDto;
    }


}
