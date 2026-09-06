package com.daily_notes.notes.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PreSignedUrlDto {

    private String fileName;
    private String filePath;
    private String contentType;
    private String fileSize;
    private String noteId;
}
