package com.example.userapplication.service;

import com.example.userapplication.dto.RegisterUserDto;
import com.example.userapplication.dto.RegistrationResultDto;
import com.example.userapplication.exception.UserAlreadyExistException;
import com.example.userapplication.model.MyUser;
import com.example.userapplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class LoginService {

    private final UserRepository userRepository;

    public RegistrationResultDto registerUser(RegisterUserDto registerUserDto) {
        final MyUser myUser = new MyUser(registerUserDto.name(), registerUserDto.password(), registerUserDto.email());
        try {
            MyUser savedUser = userRepository.save(myUser);
            return new RegistrationResultDto(savedUser.getId(), true, savedUser.getName());
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistException("User already exist in database");
        }
    }

    public boolean login(RegisterUserDto registerUserDto) {
        return userRepository.findById(registerUserDto.name()).isPresent();
    }

    public MyUser updateUser (RegisterUserDto registerUserDto) {
        final MyUser myUser = new MyUser(registerUserDto.name(), registerUserDto.password(), registerUserDto.email());
        MyUser updatedUser = userRepository.save(myUser);
        return updatedUser;
    }

//    public MyUser changeEmail(MyUser user, String email) {
//        MyUser userToSave = new MyUser(user.getName(), user.getPassword(), email);
//        userRepository.save(userToSave);
//        return userToSave;
//    }
//
//    public MyUser changePassword(MyUser user, String password) {
//        MyUser userToSave = new MyUser(user.getName(), password, user.getEmail());
//        userRepository.save(userToSave);
//        return userToSave;
//    }

    public void removeUser(RegisterUserDto registerUserDto) {
        final MyUser myUser = new MyUser(registerUserDto.name(), registerUserDto.password(), registerUserDto.email());
        userRepository.delete(myUser);
    }

    public List<MyUser> getUsers() {
        return userRepository.findAll();
    }

    public boolean ifUserExist(RegisterUserDto registerUserDto) {
        return userRepository.findById(registerUserDto.name()).isPresent();
    }

}
