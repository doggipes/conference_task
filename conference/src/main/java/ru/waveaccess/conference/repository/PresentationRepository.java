package ru.waveaccess.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.waveaccess.conference.model.entity.Presentation;

import java.util.Optional;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    Optional<Presentation> findByName(String name);

    @Modifying
    @Query("UPDATE Presentation u set u.name = ?1 where u.id = ?2")
    void updatePresentationName(String name, Long id);
}
