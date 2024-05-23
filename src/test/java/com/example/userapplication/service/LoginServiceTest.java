package com.example.userapplication.service;

import com.example.userapplication.dto.RegisterUserDto;
import com.example.userapplication.dto.RegistrationResultDto;
import com.example.userapplication.model.MyUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
    private final LoginService facade = new LoginService(new InMemoryUserRepositoryTestImp());

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
        RegisterUserDto registerUserDto = new RegisterUserDto("Name", "password", "email");
        facade.registerUser(registerUserDto);
        //        LoginAndRegisterFacade facade = new LoginAndRegisterFacade();

        //when
        RegistrationResultDto registrationResultDto = facade.registerUser(registerUserDto);

        //then
//        Throwable exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> {
//                    throw new IllegalArgumentException("Exception message");
//                }
//        );
//        assertEquals("Exception message", exception.getMessage());
        assertFalse(registrationResultDto.ifUserRegister());

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