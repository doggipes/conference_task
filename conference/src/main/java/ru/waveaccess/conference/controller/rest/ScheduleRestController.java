package ru.waveaccess.conference.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.waveaccess.conference.mapper.ScheduleDtoMapper;
import ru.waveaccess.conference.model.dto.ScheduleDto;
import ru.waveaccess.conference.service.interfaces.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleRestController {
        private final ScheduleService scheduleService;
        private final ScheduleDtoMapper scheduleDtoMapper;

        @GetMapping("/schedule/api/room/{number}")
        public List<ScheduleDto> getScheduleByRoom(@PathVariable String number){
            return scheduleDtoMapper.to(scheduleService.findByRoom(number));
        }

}
