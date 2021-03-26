package ru.waveaccess.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.waveaccess.conference.model.entity.Room;
import ru.waveaccess.conference.model.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByRoom(Room room);
}
