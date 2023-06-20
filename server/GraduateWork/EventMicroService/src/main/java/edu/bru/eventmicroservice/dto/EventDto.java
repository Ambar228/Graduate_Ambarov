package edu.bru.eventmicroservice.dto;

import edu.bru.eventmicroservice.model.enums.StateEvent;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
public class EventDto {
    private String name;

    private String description;

    private String location;

    private MultipartFile picture;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String state;

    private String type;
}
