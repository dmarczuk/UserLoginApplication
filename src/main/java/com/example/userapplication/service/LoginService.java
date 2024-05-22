package com.example.userapplication.service;

import com.example.userapplication.model.MyUser;
import com.example.userapplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginService {

    private final UserRepository userRepository;

    public MyUser registerUser(MyUser user) {
        userRepository.save(user);
        return user;
    }

    public boolean login(MyUser user) {
        return userRepository.findById(user.getName()).isPresent();
    }

    public MyUser changeEmail(MyUser user, String email) {
        MyUser userToSave = new MyUser(user.getName(), user.getPassword(), email);
        userRepository.save(userToSave);
        return userToSave;
    }

    public MyUser changePassword(MyUser user, String password) {
        MyUser userToSave = new MyUser(user.getName(), password, user.getEmail());
        userRepository.save(userToSave);
        return userToSave;
    }

    public void removeUser(MyUser user) {
        userRepository.delete(user);
    }

    public boolean ifUserExist(MyUser user) {
        return userRepository.findById(user.getName()).isPresent();
    }

}
