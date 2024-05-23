package com.example.userapplication.service;

import com.example.userapplication.dto.RegisterUserDto;
import com.example.userapplication.dto.RegistrationResultDto;
import com.example.userapplication.exception.UserAlreadyExistException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
    private final UserService facade = new UserService(new InMemoryUserRepositoryTestImp());

    @Test
    public void should_successfully_register_user() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("Name", "password", "email");

        //when
        RegistrationResultDto registrationResultDto = facade.registerUser(registerUserDto);

        //then
        assertTrue(registrationResultDto.ifUserRegister());

    }

    @Test
    public void should_not_register_user_who_already_exist_in_database() {
        //given
        RegisterUserDto registeredUser = new RegisterUserDto("Name", "password", "email");
        RegisterUserDto userToRegister = new RegisterUserDto("Name", "password", "email");
        facade.registerUser(registeredUser);
        facade.registerUser(registeredUser);

        //when
        Throwable thrown = catchThrowable(() -> facade.registerUser(userToRegister));

        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(UserAlreadyExistException.class)
                .hasMessage("User already exist in database");
    }

    @Test
    public void should_success_login_user() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("Name", "password", "email");
        facade.registerUser(registerUserDto);

        //when
        boolean ifUserLogin = facade.login(registerUserDto);

        //then
        assertTrue(ifUserLogin);

    }

    @Test
    public void should_not_login_user_when_user_not_exist_in_database() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("Name", "password", "email");

        //when
        boolean ifUserLogin = facade.login(registerUserDto);

        //then
        assertFalse(ifUserLogin);
    }

    @Test
    public void should_update_user_email() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("Name", "password", "email");
        facade.registerUser(registerUserDto);

        //when
        RegisterUserDto userToUpdate = new RegisterUserDto("Name", "password", "newEmail");
        facade.updateUser(userToUpdate);
        boolean userExist = facade.ifUserExist(new RegisterUserDto("Name", "password", "newEmail"));

        //then
        assertTrue(userExist);
    }

    @Test
    public void should_update_user_password() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("Name", "password", "email");
        facade.registerUser(registerUserDto);

        //when
        RegisterUserDto userToUpdate = new RegisterUserDto("Name", "newPassword", "email");
        facade.updateUser(userToUpdate);
        boolean userExist = facade.ifUserExist(new RegisterUserDto("Name", "newPassword", "email"));

        //then
        assertTrue(userExist);
    }

    @Test
    public void should_delete_user_who_exist_in_database() {
        //given
        RegisterUserDto registerUserDto = new RegisterUserDto("Name", "password", "email");
        facade.registerUser(registerUserDto);

        //when
        facade.removeUser(registerUserDto);
        boolean ifUserDeleted = facade.ifUserExist(registerUserDto);

        //then
        assertFalse(ifUserDeleted);
    }


}