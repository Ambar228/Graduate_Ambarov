package edu.bru.usermicroservice.converter.impl;

import edu.bru.usermicroservice.converter.UserDtoConverter;
import edu.bru.usermicroservice.dto.UserRegistrationDto;
import edu.bru.usermicroservice.model.User;
import edu.bru.usermicroservice.model.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverterImpl implements UserDtoConverter {
    @Override
    public User convertFromDto(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .name(userRegistrationDto.getName())
                .lastname(userRegistrationDto.getLastname())
                .numberPhone(userRegistrationDto.getNumberPhone())
                .password(userRegistrationDto.getPassword())
                .role(UserRole.USER)
                .build();
    }
}
