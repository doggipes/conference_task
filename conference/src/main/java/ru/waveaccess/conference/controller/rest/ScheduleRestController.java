package ru.waveaccess.conference.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.waveaccess.conference.model.entity.Schedule;
import ru.waveaccess.conference.service.interfaces.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleRestController {
        private final ScheduleService scheduleService;

        @GetMapping("/schedule/api/room/{number}")
        public List<Schedule> getScheduleByRoom(@PathVariable String number){
            return scheduleService.findByRoom(number);
        }

}
