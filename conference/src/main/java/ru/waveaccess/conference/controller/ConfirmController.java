package ru.waveaccess.conference.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.waveaccess.conference.service.interfaces.UserService;

@Controller
@RequiredArgsConstructor
public class ConfirmController {
    private final UserService userService;

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    @ResponseBody
    public String getMainWithTokenParameterController(@RequestParam String token) {
        return userService.userConfirmation(token) ? "Success" : "Something going wrong";
    }

}
