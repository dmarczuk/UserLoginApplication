package com.example.userapplication.domain.login;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        userRepository.save(user);
        return user;
    }

    public boolean login(User user) {
        return userRepository.findById(user.name()).isPresent();
    }

    public User changeEmail(User user, String email) {
        User userToSave = new User(user.name(), user.password(), email);
        userRepository.save(userToSave);
        return userToSave;
    }

    public User changePassword(User user, String password) {
        User userToSave = new User(user.name(), password, user.email());
        userRepository.save(userToSave);
        return userToSave;
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public boolean ifUserExist(User user) {
        return userRepository.findById(user.name()).isPresent();
    }

}
