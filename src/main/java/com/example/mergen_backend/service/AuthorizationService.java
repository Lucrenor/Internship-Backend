package com.example.mergen_backend.service;

import com.example.mergen_backend.entity.Authorization;
import com.example.mergen_backend.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationRepository authorizationRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthorizationService(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean authenticate(String username, String password) {
        Authorization authorization = authorizationRepository.findByUsername(username);
        if (authorization != null) {
            return passwordEncoder.matches(password, authorization.getPassword());
        }
        return false;
    }

    public void registerUser(String username, String password) {
        Authorization authorization = new Authorization();
        authorization.setUsername(username);
        authorization.setPassword(passwordEncoder.encode(password));
        authorizationRepository.save(authorization);
    }
}