package com.example.userapplication.service;

import com.example.userapplication.dto.RegisterUserDto;
import com.example.userapplication.dto.RegistrationResultDto;
import com.example.userapplication.dto.UpdateUserResultDto;
import com.example.userapplication.exception.UserAlreadyExistException;
import com.example.userapplication.model.MyUser;
import com.example.userapplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public UpdateUserResultDto updateUser (RegisterUserDto registerUserDto) {
        Optional<MyUser> user = userRepository.findByName(registerUserDto.name());
        if (user.isPresent()) {
            user.get().setName(registerUserDto.name());
            user.get().setPassword(registerUserDto.password());
            user.get().setEmail(registerUserDto.email());
            userRepository.save(user.get());
        }
        return new UpdateUserResultDto(user.isPresent(), registerUserDto.name());
    }

    public void removeUser(RegisterUserDto registerUserDto) {
        Optional<MyUser> user = userRepository.findByName(registerUserDto.name());
        user.ifPresent(userRepository::delete);
    }

    public List<MyUser> getUsers() {
        return userRepository.findAll();
    }

    public boolean ifUserExist(RegisterUserDto registerUserDto) {
        return userRepository.findByName(registerUserDto.name()).isPresent();
    }

}
