package ru.waveaccess.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.waveaccess.conference.model.entity.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
   Optional<Room> findByName(String name);
}
