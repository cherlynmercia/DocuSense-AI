package com.docusense.backend.controller;

import com.docusense.backend.model.UploadResponse;
import com.docusense.backend.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public UploadResponse uploadFile(
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) String link
    ) throws Exception {

        if (file != null && !file.isEmpty()) {
            return uploadService.handleFileUpload(file);
        }

        if (link != null && !link.isEmpty()) {
            return new UploadResponse(
                    "success",
                    "link",
                    null,
                    0,
                    "Link received: " + link
            );
        }

        return new UploadResponse(
                "error",
                null,
                null,
                0,
                "No file or link provided"
        );
    }
}