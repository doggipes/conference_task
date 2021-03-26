package ru.waveaccess.conference.service.interfaces;

import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.entity.VerificationToken;

public interface TokenService {
    VerificationToken createToken(User user);

    User findUserByToken(String token);

    void deleteToken(String token);
}
