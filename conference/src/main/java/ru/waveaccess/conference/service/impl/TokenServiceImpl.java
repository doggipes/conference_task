package ru.waveaccess.conference.service.impl;

import org.springframework.stereotype.Service;
import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.entity.VerificationToken;
import ru.waveaccess.conference.repository.TokenRepository;
import ru.waveaccess.conference.service.interfaces.TokenService;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public VerificationToken createToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                                                                .token(token)
                                                                .expiryDate(new Timestamp(System.currentTimeMillis()))
                                                                .user(user)
                                                                .build();
        tokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findUserByToken(String token) {
        List<VerificationToken> list = tokenRepository.findUserByToken(token);

        return list.isEmpty() ? null : list.get(0).getUser();
    }

    @Override
    public void deleteToken(String token) {
        tokenRepository.deleteByToken(token);
    }
}
