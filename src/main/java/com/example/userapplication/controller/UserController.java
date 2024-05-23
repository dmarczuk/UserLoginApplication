package com.example.userapplication.controller;

import com.example.userapplication.dto.RegisterUserDto;
import com.example.userapplication.dto.RegistrationResultDto;
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
    public ResponseEntity<MyUser> login(@RequestBody MyUser user ) {
        boolean ifUserLogged = loginService.login(user);
        if(ifUserLogged) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
    }

    @PostMapping("/update")
    public ResponseEntity<MyUser> update (@RequestBody MyUser user ) {
        MyUser registeredUser = loginService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(registeredUser);
    }

    @PostMapping("/remove")
    public ResponseEntity<MyUser> remove (@RequestBody MyUser user ) {
        loginService.removeUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
