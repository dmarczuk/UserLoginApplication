package com.example.userapplication.error;

import com.example.userapplication.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RegisterControllerErrorHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public UserExistErrorResponse userAlreadyExistInDatabase(UserAlreadyExistException exception) {
        String message = exception.getMessage();
        return new UserExistErrorResponse(message, HttpStatus.CONFLICT);
    }
}
