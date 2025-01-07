package com.example.spring_render.controller;

import com.example.spring_render.model.Image;
import com.example.spring_render.repository.ImageRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yana.zhmak on 16.12.2024.
 */

@RestController
public class ImageController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ImageRepository imageRepository;

    /**
     * Endpoint for uploading multiple images
     * @param files to be uploaded
     * @return ResponseEntity List of file uploaded results
     */

    @Operation(summary = "Upload images")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image uploaded successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/uploadImage")
    public ResponseEntity<List<String>> uploadImages(@RequestParam MultipartFile[] files) {
        List<String> fileUploadResults = new ArrayList<>();

        for (MultipartFile file: files) {
            try {
                String filePath = saveImage(file);
                fileUploadResults.add("Image uploaded successfully" + filePath);
            } catch (IOException e) {
                fileUploadResults.add("Error uploading image");
            }
        }
        return ResponseEntity.ok(fileUploadResults);
    }

//    @PostMapping("/uploadImageToDb")
//    public ResponseEntity<List<String>> uploadImagesToDb(@RequestParam MultipartFile[] files) {
//        List<String> fileUploadResults = new ArrayList<>();
//
//        for (MultipartFile file: files) {
//            try {
//                Image image = new Image();
//                image.setData(file.getBytes());
//                image.setFileName(file.getOriginalFilename());
//                image.setUploadedTime(LocalDateTime.now());
//                imageRepository.save(image);
//
//                fileUploadResults.add("Image uploaded successfully" + file.getOriginalFilename());
//            } catch (IOException e) {
//                fileUploadResults.add("Error uploading image");
//            }
//        }
//        return ResponseEntity.ok(fileUploadResults);
//    }

    /**
     * Method to save images
     * @param file to be saved
     * @return File path to saved image
     * @throws IOException
     */
    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        file.getBytes();
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}
