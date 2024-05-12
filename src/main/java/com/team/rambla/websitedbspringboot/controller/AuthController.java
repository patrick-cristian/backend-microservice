package com.team.rambla.websitedbspringboot.controller;

import com.team.rambla.websitedbspringboot.entity.ERoles;
import com.team.rambla.websitedbspringboot.entity.Role;
import com.team.rambla.websitedbspringboot.entity.Users;
import com.team.rambla.websitedbspringboot.payload.request.SignupRequest;
import com.team.rambla.websitedbspringboot.repository.RoleRepository;
import com.team.rambla.websitedbspringboot.repository.UserRepository;
import com.team.rambla.websitedbspringboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupUser) {
        if (userRepository.existsByUsername(signupUser.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }

        List<Role> roles = new ArrayList<>();
        if (!roleRepository.existsByName(ERoles.ROLE_ADMIN)) {
            roleRepository.save(new Role(ERoles.ROLE_ADMIN));
        }

        Role userRole = roleRepository.findByName(ERoles.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(userRole);
        Users users = new Users(signupUser.getUsername(),
                passwordEncoder.encode(signupUser.getPassword()),
                signupUser.getEmail(),
                signupUser.getFirstName(),
                signupUser.getLastName(),
                roles);
        userRepository.save(users);
        return ResponseEntity.ok().body(users);
    }
}
