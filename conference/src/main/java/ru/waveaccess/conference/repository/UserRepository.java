package ru.waveaccess.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.enums.Role;
import ru.waveaccess.conference.model.enums.State;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Modifying
    @Query("UPDATE User u set u.state = ?1 where u.id = ?2")
    void updateUserState(State state, Long id);

    @Modifying
    @Query("UPDATE User u set u.name = ?1 where u.id = ?2")
    void updateUserName(String name, Long id);

    @Modifying
    @Query("UPDATE User u set u.role = ?1 where u.id = ?2")
    void updateUserRole(Role role, Long id);
}
