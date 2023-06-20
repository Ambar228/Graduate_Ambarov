package edu.bru.usermicroservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthenticationDto {
    private String numberPhone;

    private String password;
}
