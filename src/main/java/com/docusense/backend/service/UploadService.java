package com.docusense.backend.service;

import com.docusense.backend.model.UploadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

    public UploadResponse handleFileUpload(MultipartFile file) throws IOException {

        // Get project root path
        String projectPath = System.getProperty("user.dir");
        String uploadDir = projectPath + File.separator + "uploads";

        // Create uploads directory if not exists
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Clean file name (replace spaces)
        String fileName = file.getOriginalFilename().replaceAll("\\s+", "_");

        File destinationFile = new File(uploadDir + File.separator + fileName);
        file.transferTo(destinationFile);

        // Detect file type
        String fileType = detectFileType(fileName);

        return new UploadResponse(
                "success",
                fileType,
                fileName,
                file.getSize(),
                "File uploaded successfully"
        );
    }

    private String detectFileType(String fileName) {

        String lower = fileName.toLowerCase();

        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".png")) {
            return "image";
        } else if (lower.endsWith(".mp4")) {
            return "video";
        } else {
            return "unsupported";
        }
    }
}