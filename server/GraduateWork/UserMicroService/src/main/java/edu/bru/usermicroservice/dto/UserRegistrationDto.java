package edu.bru.usermicroservice.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {
    private String name;

    private String lastname;

    private String numberPhone;

    private String password;
}
