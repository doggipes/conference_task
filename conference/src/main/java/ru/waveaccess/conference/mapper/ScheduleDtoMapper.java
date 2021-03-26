package ru.waveaccess.conference.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.waveaccess.conference.model.dto.ScheduleDto;
import ru.waveaccess.conference.model.entity.Schedule;
import ru.waveaccess.conference.service.interfaces.PresentationService;
import ru.waveaccess.conference.service.interfaces.RoomService;

@Component
@RequiredArgsConstructor
public class ScheduleDtoMapper implements Mapper<Schedule, ScheduleDto>{
    private final RoomService roomService;
    private final PresentationService presentationService;

    @Override
    public Schedule from(ScheduleDto scheduleDto) {
        return Schedule.builder()
                        .room(roomService.findByNumber(scheduleDto.getRoom()))
                        .presentation(presentationService.findById(Long.parseLong(scheduleDto.getPresentation())))
                        .startDate(scheduleDto.getStartDate())
                        .endDate(scheduleDto.getEndDate())
                        .build();
    }

    @Override
    public ScheduleDto to(Schedule schedule) {
        return ScheduleDto.builder()
                .room(schedule.getRoom().getName())
                .presentation(schedule.getPresentation().getName())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .build();
    }


}
