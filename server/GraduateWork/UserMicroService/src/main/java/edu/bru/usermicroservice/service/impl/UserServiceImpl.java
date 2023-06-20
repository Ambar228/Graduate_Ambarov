package edu.bru.usermicroservice.service.impl;

import edu.bru.usermicroservice.converter.UserDtoConverter;
import edu.bru.usermicroservice.dto.UserRegistrationDto;
import edu.bru.usermicroservice.model.Racer;
import edu.bru.usermicroservice.model.User;
import edu.bru.usermicroservice.model.enums.UserRole;
import edu.bru.usermicroservice.repository.RacerRepository;
import edu.bru.usermicroservice.repository.UserRepository;
import edu.bru.usermicroservice.security.userDetails.JwtUser;
import edu.bru.usermicroservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final RacerRepository racerRepository;

    @Override
    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll().stream().filter(user -> user.getRole() != UserRole.ADMIN).toList();
    }

    @Override
    @Transactional
    public List<Racer> getRacers() {
        return (List<Racer>) racerRepository.findAll();
    }

    @Override
    @Transactional
    public UserRole checkRole(JwtUser jwtUser) {
        return userRepository.findByNumberPhone(jwtUser.getUsername()).getRole();
    }

    @Override
    @Transactional
    public void registration(UserRegistrationDto userRegistrationDto) {
        User user = userDtoConverter.convertFromDto(userRegistrationDto);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addRacer(Long userId) {
        User user = userRepository.findById(userId).get();

        if (!racerRepository.existsById(userId)) {
            Racer racer = Racer.builder()
                    .name(user.getName())
                    .lastname(user.getLastname())
                    .build();

            racerRepository.save(racer);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String numberPhone) throws UsernameNotFoundException {
        User user = userRepository.findByNumberPhone(numberPhone);
        return new JwtUser(user);
    }
}
