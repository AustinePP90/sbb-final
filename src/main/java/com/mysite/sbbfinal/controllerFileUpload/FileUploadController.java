package com.mysite.sbbfinal.controllerFileUpload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Value("${file.upload.directory}")
    private String uploadDirectory;
	
    // 파일 업로드 양식
    @GetMapping("/fileform")
    public String fileForm() {
        return "file-form";
    }
    
    // 단일 파일 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 파일명 충돌 방지를 위한 고유 파일명 생성
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

            // 파일 저장 경로 설정
            Path filePath = Paths.get(uploadDirectory, uniqueFilename);
            // 파일 저장
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("File uploaded successfully: " + uniqueFilename);
            
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }
    
    // 다중 파일 업로드
    @PostMapping("/upload-multiple")
    public ResponseEntity<List<String>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        List<String> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
            	// 파일명 충돌 방지를 위한 고유 파일명 생성
                String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                // 파일 저장 경로 설정
                Path filePath = Paths.get(uploadDirectory, uniqueFilename);
                // 파일 저장
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                uploadedFiles.add(uniqueFilename);
                
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(List.of("Failed to upload files: " + e.getMessage()));
            }
        }

        return ResponseEntity.ok(uploadedFiles);
    }
}
