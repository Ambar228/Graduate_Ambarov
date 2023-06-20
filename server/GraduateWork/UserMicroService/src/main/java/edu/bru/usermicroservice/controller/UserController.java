package edu.bru.usermicroservice.controller;

import edu.bru.usermicroservice.dto.UserAuthenticationDto;
import edu.bru.usermicroservice.dto.UserRegistrationDto;
import edu.bru.usermicroservice.model.Racer;
import edu.bru.usermicroservice.model.User;
import edu.bru.usermicroservice.model.enums.UserRole;
import edu.bru.usermicroservice.repository.UserRepository;
import edu.bru.usermicroservice.security.JwtUtil;
import edu.bru.usermicroservice.security.userDetails.JwtUser;
import edu.bru.usermicroservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @GetMapping("/{userId}")
    public ResponseEntity<Void> addRacer(@PathVariable Long userId) {
        userService.addRacer(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/moderator/{userId}")
    public ResponseEntity<Void> addModerator(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        user.setRole(UserRole.MODERATOR);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/racers")
    public ResponseEntity<List<Racer>> getRacers() {
        return ResponseEntity.ok(userService.getRacers());
    }


    @GetMapping("/check")
    public ResponseEntity<UserRole> checkRole(@AuthenticationPrincipal JwtUser jwtUser) {
        return ResponseEntity.ok(userService.checkRole(jwtUser));
    }

    @PostMapping("/authentication")
    public ResponseEntity<String> authenticate(@RequestBody UserAuthenticationDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getNumberPhone(), user.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getNumberPhone());

        if (userDetails == null) {
            throw new BadCredentialsException("Not Valid email or password");
        }

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDto userRegistrationDto) {
        String password = userRegistrationDto.getPassword();
        userRegistrationDto.setPassword(new BCryptPasswordEncoder().encode(userRegistrationDto.getPassword()));
        userService.registration(userRegistrationDto);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegistrationDto.getNumberPhone(), password)
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userRegistrationDto.getNumberPhone());

        if (userDetails == null) {
            throw new BadCredentialsException("Not Valid email or password");
        }

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }
}
