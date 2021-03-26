package ru.waveaccess.conference.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.waveaccess.conference.model.form.ScheduleForm;
import ru.waveaccess.conference.service.interfaces.PresentationService;
import ru.waveaccess.conference.service.interfaces.RoomService;
import ru.waveaccess.conference.service.interfaces.ScheduleService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.DateTimeException;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final RoomService roomService;
    private final PresentationService presentationService;

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String getPresentationController(Model model) {
        model.addAttribute("ScheduleForm", new ScheduleForm());
        model.addAttribute("rooms", roomService.getAllRoomNames());
        model.addAttribute("presentations", presentationService.getMapWithAllPresentations());

        return "schedule";
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public String postPresentationController(@Valid ScheduleForm scheduleForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors())
            scheduleService.createSchedule(scheduleForm);

        model.addAttribute("ScheduleForm", scheduleForm);
        model.addAttribute("rooms", roomService.getAllRoomNames());
        model.addAttribute("presentations", presentationService.getMapWithAllPresentations());

        return "schedule";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({EntityNotFoundException.class, DateTimeException.class})
    public String handleNotFoundError(Exception e, Model model) {
        model.addAttribute("ScheduleForm", new ScheduleForm());
        model.addAttribute("rooms", roomService.getAllRoomNames());
        model.addAttribute("presentations", presentationService.getMapWithAllPresentations());
        model.addAttribute("error", "Error: " + e.getMessage());
        return "schedule";
    }


}
