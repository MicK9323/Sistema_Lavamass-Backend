package com.mca.apps.lavamassapirest.controllers;

import com.mca.apps.lavamassapirest.exception.AppException;
import com.mca.apps.lavamassapirest.models.model.RoleDTO;
import com.mca.apps.lavamassapirest.models.model.UserDTO;
import com.mca.apps.lavamassapirest.models.repository.RoleRepository;
import com.mca.apps.lavamassapirest.models.repository.UserRepository;
import com.mca.apps.lavamassapirest.payloads.request.LoginRequest;
import com.mca.apps.lavamassapirest.payloads.request.SignUpRequest;
import com.mca.apps.lavamassapirest.payloads.response.ApiResponse;
import com.mca.apps.lavamassapirest.payloads.response.JwtAuthenticationResponse;
import com.mca.apps.lavamassapirest.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getDniOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByDni(signUpRequest.getDni())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserDTO user = new UserDTO(signUpRequest.getDni(), signUpRequest.getPasswd(), signUpRequest.getName(),
                signUpRequest.getLastName(), signUpRequest.getTelf(),signUpRequest.getAddress(), signUpRequest.getEmail());

        user.setPasswd(passwordEncoder.encode(signUpRequest.getPasswd()));

        RoleDTO userRole = roleRepository.findByRoleName("ROLE_ADMIN")
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        UserDTO result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getCodUser()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}