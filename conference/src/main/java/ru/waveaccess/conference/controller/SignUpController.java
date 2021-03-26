package ru.waveaccess.conference.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.waveaccess.conference.model.form.SignUpForm;
import ru.waveaccess.conference.service.interfaces.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignUpController(Authentication authentication, Model model){
        if(authentication != null )
            return "redirect:/start";
        else {
            model.addAttribute("SignUpForm", new SignUpForm());
            return "sign-up";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String postSignUpController(@Valid SignUpForm form, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("SignUpForm", form);
        }
        else {
            userService.register(form);
            model.addAttribute("SignUpForm", new SignUpForm());
        }

        return "sign-up";
    }
}
