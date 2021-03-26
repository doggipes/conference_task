package ru.waveaccess.conference.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.enums.Role;
import ru.waveaccess.conference.model.form.ScheduleForm;
import ru.waveaccess.conference.security.details.UserDetailsImpl;
import ru.waveaccess.conference.service.interfaces.RoomService;
import ru.waveaccess.conference.service.interfaces.ScheduleService;
import ru.waveaccess.conference.service.interfaces.UserPresentationService;

import javax.persistence.EntityNotFoundException;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final RoomService roomService;
    private final ScheduleService scheduleService;
    private final UserPresentationService userPresentationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainController(Model model, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        if(!user.getRole().equals(Role.LISTENER)){
            model.addAttribute("presentationPage", true);
            model.addAttribute("schedulePage", true);
        }

        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("schedules", scheduleService.getAllSchedule());
        return "main";
    }

    @RequestMapping(value = "/join/{id}", method = RequestMethod.POST)
    public String postMainController(Model model, @PathVariable("id") Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        userPresentationService.save(user.getId(), id);

        return "redirect:/";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({EntityNotFoundException.class})
    public String handleNotFoundError(Exception e, Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("schedules", scheduleService.getAllSchedule());
        model.addAttribute("error", "Error: " + e.getMessage());
        return "schedule";
    }


}
