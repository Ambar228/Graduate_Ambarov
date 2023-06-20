package edu.bru.usermicroservice.converter;

import edu.bru.usermicroservice.dto.UserAuthenticationDto;
import edu.bru.usermicroservice.dto.UserRegistrationDto;
import edu.bru.usermicroservice.model.User;

public interface UserDtoConverter {
    User convertFromDto(UserRegistrationDto userRegistrationDto);
}
