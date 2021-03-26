package ru.waveaccess.conference.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.waveaccess.conference.mapper.ScheduleDtoMapper;
import ru.waveaccess.conference.model.dto.ScheduleDto;
import ru.waveaccess.conference.model.entity.Room;
import ru.waveaccess.conference.model.entity.Schedule;
import ru.waveaccess.conference.model.form.ScheduleForm;
import ru.waveaccess.conference.repository.ScheduleRepository;
import ru.waveaccess.conference.service.interfaces.ScheduleService;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleDtoMapper scheduleDtoMapper;

    @Override
    public void createSchedule(ScheduleForm scheduleForm) {
        ScheduleDto scheduleDto = new ScheduleDto(scheduleForm);
        Schedule schedule = scheduleDtoMapper.from(scheduleDto);

        if (!validationDates(schedule))
            throw new DateTimeException("Wrong time");

        scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findByRoom(Room room) {
        return scheduleRepository.findAllByRoom(room);
    }

    @Override
    public List<Schedule> findByRoom(String number) {
        return scheduleRepository.findByRoom(number);
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }


    private boolean validationDates(Schedule schedule) {
        if (!checkDates(schedule.getStartDate(), schedule.getEndDate()))
            return false;

        List<Schedule> schedulesWithThisRoom = findByRoom(schedule.getRoom());
        Timestamp startDate = schedule.getStartDate();
        Timestamp endDate = schedule.getEndDate();

        return schedulesWithThisRoom.stream().noneMatch(s -> (s.getStartDate().before(endDate) && startDate.before(s.getEndDate())));
    }

    private boolean checkDates(Timestamp startTime, Timestamp endTime) {
        return startTime.before(endTime);
    }
}
