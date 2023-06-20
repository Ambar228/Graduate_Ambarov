package edu.bru.commentmicroservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CommentDto {
    private String text;

    private MultipartFile file;
}
