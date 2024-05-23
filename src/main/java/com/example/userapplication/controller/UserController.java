package com.example.userapplication.controller;

import com.example.userapplication.dto.*;
import com.example.userapplication.model.MyUser;
import com.example.userapplication.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final LoginService loginService;

    @GetMapping("/users")
    public List<MyUser> getUsers() {
        return loginService.getUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@RequestBody RegisterUserDto registerUserDto ) {
        RegistrationResultDto registrationResultDto = loginService.registerUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResultDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResultDto> login(@RequestBody RegisterUserDto registerUserDto ) {
        boolean ifUserLogged = loginService.login(registerUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResultDto(ifUserLogged, registerUserDto.name()));

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
