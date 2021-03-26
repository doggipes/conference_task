package ru.waveaccess.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.entity.VerificationToken;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query(value = "SELECT v FROM VerificationToken v WHERE v.token = ?1")
    List<VerificationToken> findUserByToken(String token);

    void deleteByToken(String token);
}
