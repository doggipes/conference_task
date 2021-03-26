package ru.waveaccess.conference.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExist extends AuthenticationException {
    public UserAlreadyExist(String msg) {
        super(msg);
    }
}