package ru.waveaccess.conference.service.interfaces;

import ru.waveaccess.conference.model.entity.Room;
import ru.waveaccess.conference.model.entity.Schedule;
import ru.waveaccess.conference.model.form.ScheduleForm;

import java.util.List;

public interface ScheduleService {
    void createSchedule(ScheduleForm scheduleForm);

    List<Schedule> findByRoom(Room room);

    List<Schedule> getAllSchedule();

//    List<Schedule> getAllScheduleWithCustomDate();
}
