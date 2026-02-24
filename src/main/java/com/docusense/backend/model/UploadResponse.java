package com.docusense.backend.model;

public class UploadResponse {

    private String status;
    private String type;
    private String fileName;
    private long size;
    private String message;

    public UploadResponse(String status, String type, String fileName, long size, String message) {
        this.status = status;
        this.type = type;
        this.fileName = fileName;
        this.size = size;
        this.message = message;
    }

    public String getStatus() { return status; }
    public String getType() { return type; }
    public String getFileName() { return fileName; }
    public long getSize() { return size; }
    public String getMessage() { return message; }
}