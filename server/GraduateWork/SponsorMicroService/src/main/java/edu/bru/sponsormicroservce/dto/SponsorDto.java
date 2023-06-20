package edu.bru.sponsormicroservce.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class SponsorDto {

    private String name;

    private String advertisingInformation;

    private String contacts;

    private MultipartFile logo;
}
