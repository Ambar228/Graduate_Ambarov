package edu.bru.usermicroservice.service;

import edu.bru.usermicroservice.dto.UserRegistrationDto;
import edu.bru.usermicroservice.model.Racer;
import edu.bru.usermicroservice.model.User;
import edu.bru.usermicroservice.model.enums.UserRole;
import edu.bru.usermicroservice.security.userDetails.JwtUser;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    List<Racer> getRacers();
    UserRole checkRole(JwtUser jwtUser);
    void registration(UserRegistrationDto userRegistrationDto);
    void addRacer(Long userId);
}
