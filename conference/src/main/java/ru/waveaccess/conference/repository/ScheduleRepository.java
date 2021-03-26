package ru.waveaccess.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.waveaccess.conference.model.entity.Room;
import ru.waveaccess.conference.model.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByRoom(Room room);

    @Modifying
    @Query("SELECT u FROM Schedule u where u.room.name = ?1")
    List<Schedule> findByRoom(String number);
}
