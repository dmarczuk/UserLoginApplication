package com.example.userapplication.controller;

import com.example.userapplication.dto.*;
import com.example.userapplication.model.MyUser;
import com.example.userapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService loginService;
    private final AuthenticationManager authenticationManager;

//    public UserController(AuthenticationManager authenticationManager, UserService loginService) {
//        this.loginService = loginService;
//        this.authenticationManager = authenticationManager;
//    }

    @GetMapping("/users")
    public List<MyUser> getUsers() {
        return loginService.getUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@Valid @RequestBody RegisterUserDto registerUserDto ) {
        RegistrationResultDto registrationResultDto = loginService.registerUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResultDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResultDto> login(@RequestBody RegisterUserDto registerUserDto ) {
        try {
            Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(registerUserDto.name(), registerUserDto.password());
            authenticationManager.authenticate(authenticationRequest);
            SecurityContextHolder.getContext().setAuthentication(authenticationRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResultDto(true, registerUserDto.name(), "Successful login"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResultDto(false, registerUserDto.name(), "Invalid username or password"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateUserResultDto> update (@RequestBody RegisterUserDto registerUserDto) {
        boolean userExist = loginService.ifUserExist(registerUserDto);
        if(userExist) {
            loginService.updateUser(registerUserDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UpdateUserResultDto(userExist, registerUserDto.name()));
    }

    @PostMapping("/remove")
    public ResponseEntity<RemoveUserResultDto> remove (@RequestBody RegisterUserDto registerUserDto) {
        loginService.removeUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RemoveUserResultDto(registerUserDto.name()));
    }


}
