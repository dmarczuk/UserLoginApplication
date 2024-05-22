package com.example.userapplication.domain.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginAndRegisterFacadeTest {

    private LoginAndRegisterFacade facade;

    @Test
    public void should_successfully_register_user() {
        //given
        User user = new User("Name", "password", "email");
//        LoginAndRegisterFacade facade = new LoginAndRegisterFacade();

        //when
        User registeredUser = facade.registerUser(user);

        //then
        assertEquals(user, registeredUser);

    }

    @Test
    public void should_not_register_user_who_already_exist_in_database() {
        //given
        User user = new User("Name", "password", "email");
        facade.registerUser(user);
        //        LoginAndRegisterFacade facade = new LoginAndRegisterFacade();

        //when
        User registeredUser = facade.registerUser(user);

        //then
//        Throwable exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> {
//                    throw new IllegalArgumentException("Exception message");
//                }
//        );
//        assertEquals("Exception message", exception.getMessage());

    }

    @Test
    public void should_success_login_user() {
        //given
        User user = new User("Name", "password", "email");
        facade.registerUser(user);

        //when
        User loggedUser = facade.login(user);

        //then
        assertEquals(user, loggedUser);

    }

    @Test
    public void should_not_login_user_when_user_not_exist_in_database() {
        //throws exception??
    }

    @Test
    public void should_update_user_email() {
        //given
        User user = new User("Name", "password", "email");
        facade.registerUser(user);

        //when
        User updatedUser = facade.changeEmail(user, "newEmail");

        //then
        assertEquals(new User("Name", "password", "newEmail"), updatedUser);

    }

    @Test
    public void should_update_user_password() {
        //given
        User user = new User("Name", "password", "email");
        facade.registerUser(user);

        //when
        User updatedUser = facade.changePassword(user, "newPassword");

        //then
        assertEquals(new User("Name", "newPassword", "email"), updatedUser);

    }

    @Test
    public void should_delete_user_who_exist_in_database() {

    }


}