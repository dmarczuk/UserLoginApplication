package com.example.userapplication.controller;

import com.example.userapplication.model.MyUser;
import com.example.userapplication.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<MyUser> register(@RequestBody MyUser user ) {
        MyUser registeredUser = loginService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
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
