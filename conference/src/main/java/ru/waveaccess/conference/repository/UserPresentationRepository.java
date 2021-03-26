package ru.waveaccess.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.waveaccess.conference.model.entity.UserPresentation;

import java.util.Optional;

@Repository
public interface UserPresentationRepository extends JpaRepository<UserPresentation, Long> {
    Optional<UserPresentation> findByUserIdAndPresentationId(Long user_id, Long presentation_id);
}
