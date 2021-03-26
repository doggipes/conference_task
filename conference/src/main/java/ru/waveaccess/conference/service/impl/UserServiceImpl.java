package ru.waveaccess.conference.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.waveaccess.conference.model.form.SignUpForm;
import ru.waveaccess.conference.exception.UserAlreadyExist;
import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.enums.Role;
import ru.waveaccess.conference.model.enums.State;
import ru.waveaccess.conference.repository.UserRepository;
import ru.waveaccess.conference.service.interfaces.TokenService;
import ru.waveaccess.conference.service.interfaces.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public void register(SignUpForm signUpForm) {
        if(userRepository.findUserByEmail(signUpForm.getEmail()).isPresent())
            throw new UserAlreadyExist("User already exist. Try another email");

        User user = User.builder()
                        .email(signUpForm.getEmail())
                        .password(bCryptPasswordEncoder.encode(signUpForm.getPassword()))
                        .name(signUpForm.getName())
                        .surname(signUpForm.getSurname())
                        .role(Role.LISTENER)
                        .state(State.NOT_CONFIRMED)
                        .build();
        userRepository.save(user);
        tokenService.createToken(user);
    }

    @Override
    @Transactional
    public boolean userConfirmation(String token) {
        User user = tokenService.findUserByToken(token);

        if(user == null)
            return false;

        userRepository.updateUserState(State.CONFIRMED, user.getId());
        tokenService.deleteToken(token);

        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateName(String name, Long id) {
        userRepository.updateUserName(name, id);
    }

    @Override
    @Transactional
    public void updateRole(String role, Long id) {
        userRepository.updateUserRole(Role.valueOf(role.toUpperCase(Locale.ROOT)), id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
