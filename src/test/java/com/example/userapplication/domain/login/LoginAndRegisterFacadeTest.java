package com.example.userapplication.domain.login;

import com.example.userapplication.model.MyUser;
import com.example.userapplication.service.LoginService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginAndRegisterFacadeTest {
    private final LoginService facade = new LoginService(new InMemoryUserRepositoryTestImp());

    @Test
    public void should_successfully_register_user() {
        //given
        MyUser user = new MyUser("Name", "password", "email");
//        LoginAndRegisterFacade facade = new LoginAndRegisterFacade();

        //when
        MyUser registeredUser = facade.registerUser(user);

        //then
        assertEquals(user, registeredUser);

    }

    @Test
    public void should_not_register_user_who_already_exist_in_database() {
        //given
        MyUser user = new MyUser("Name", "password", "email");
        facade.registerUser(user);
        //        LoginAndRegisterFacade facade = new LoginAndRegisterFacade();

        //when
        MyUser registeredUser = facade.registerUser(user);

        //then
//        Throwable exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> {
//                    throw new IllegalArgumentException("Exception message");
//                }
//        );
//        assertEquals("Exception message", exception.getMessage());
        assertTrue(false);

    }

    @Test
    public void should_success_login_user() {
        //given
        MyUser user = new MyUser("Name", "password", "email");
        facade.registerUser(user);

        //when
        boolean ifUserLogin = facade.login(user);

        //then
        assertTrue(ifUserLogin);

    }

    @Test
    public void should_not_login_user_when_user_not_exist_in_database() {
        //given
        MyUser user = new MyUser("Name", "password", "email");

        //when
        boolean ifUserLogin = facade.login(user);

        //then
        assertFalse(ifUserLogin);
    }

    @Test
    public void should_update_user_email() {
        //given
        MyUser user = new MyUser("Name", "password", "email");
        facade.registerUser(user);

        //when
        MyUser updatedUser = facade.changeEmail(user, "newEmail");
        boolean userExist = facade.ifUserExist(new MyUser("Name", "password", "newEmail"));

        //then
        assertTrue(userExist);
    }

    @Test
    public void should_update_user_password() {
        //given
        MyUser user = new MyUser("Name", "password", "email");
        facade.registerUser(user);

        //when
        MyUser updatedUser = facade.changePassword(user, "newPassword");
        boolean userExist = facade.ifUserExist(new MyUser("Name", "newPassword", "email"));

        //then
        assertTrue(userExist);
    }

    @Test
    public void should_delete_user_who_exist_in_database() {
        //given
        MyUser user = new MyUser("Name", "password", "email");
        facade.registerUser(user);

        //when
        facade.removeUser(user);
        boolean ifUserDeleted = facade.ifUserExist(user);

        //then
        assertFalse(ifUserDeleted);
    }


}