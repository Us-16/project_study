package com.example.a16sserver.retrofit.dto;


public class Upload {
    private Long id;
    private String title;
    private String content;
    private String filename;
    private String filepath;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }
}
