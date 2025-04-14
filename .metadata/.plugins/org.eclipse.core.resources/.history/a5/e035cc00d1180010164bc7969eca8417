package com.company.app.rest;

import java.io.File;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class NoteRestController {

    private String uploadPath = "src/main/resources/static/uploads/";

    @PostMapping("/note/api/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            String origin = file.getOriginalFilename();
            String filename = UUID.randomUUID().toString() + "_" + origin;
            File target = new File(uploadPath + filename);
            file.transferTo(target);
            return "/uploads/" + filename;
        } catch (Exception e) {
            e.printStackTrace();
            return "파일 업로드 실패";
        }
    }
}