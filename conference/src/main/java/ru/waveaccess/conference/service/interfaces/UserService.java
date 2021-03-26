package ru.waveaccess.conference.service.interfaces;

import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.enums.Role;
import ru.waveaccess.conference.model.form.SignUpForm;

import java.util.List;

public interface UserService {
    void register(SignUpForm signUpForm);

    boolean userConfirmation(String token);

    List<User> getAllUsers();

    void updateName(String name, Long id);

    void updateRole(String role, Long id);

    void delete(Long id);
}
