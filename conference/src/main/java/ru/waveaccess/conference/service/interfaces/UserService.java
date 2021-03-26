package ru.waveaccess.conference.service.interfaces;

import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.form.SignUpForm;

public interface UserService {
    void register(SignUpForm signUpForm);

    boolean userConfirmation(String token);
}
