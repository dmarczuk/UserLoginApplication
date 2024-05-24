package com.example.userapplication.controller;

import com.example.userapplication.dto.*;
import com.example.userapplication.model.MyUser;
import com.example.userapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
public class UserController {

    private final UserService loginService;
    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager, UserService loginService) {
        this.loginService = loginService;
        this.authenticationManager = authenticationManager;
    }

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
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(registerUserDto.name(), registerUserDto.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
//        boolean ifUserLogged = loginService.login(registerUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResultDto(authenticationResponse.isAuthenticated(), registerUserDto.name()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateUserResultDto> update (@RequestBody RegisterUserDto registerUserDto, @PathVariable long id) {
        boolean userExist = loginService.ifUserExist(registerUserDto);
        if(userExist) {
            loginService.updateUser(registerUserDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UpdateUserResultDto(userExist, registerUserDto.name()));
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<RemoveUserResultDto> remove (@RequestBody RegisterUserDto registerUserDto, @PathVariable long id) {
        loginService.removeUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(new RemoveUserResultDto(registerUserDto.name()));
    }


}
