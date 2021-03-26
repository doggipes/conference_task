package ru.waveaccess.conference.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.waveaccess.conference.model.entity.User;
import ru.waveaccess.conference.model.form.PresentationForm;
import ru.waveaccess.conference.security.details.UserDetailsImpl;
import ru.waveaccess.conference.service.interfaces.PresentationService;
import ru.waveaccess.conference.service.interfaces.UserPresentationService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PresentationController {
    private final PresentationService presentationService;
    private final UserPresentationService userPresentationService;

    @RequestMapping(value = "/presentation", method = RequestMethod.GET)
    public String getPresentationController(Model model) {
        model.addAttribute("PresentationForm", new PresentationForm());
        model.addAttribute("presentations", presentationService.getAllPresentations());
        return "presentation";
    }

    @RequestMapping(value = "/presentation", method = RequestMethod.POST)
    public String postPresentationController(@Valid PresentationForm presentationForm, Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        presentationService.createPresentation(presentationForm, user.getId());

        return "redirect:/presentation";
    }

    @RequestMapping(value = "/presentation/{id}/update", method = RequestMethod.POST)
    public String postUpdatePresentationController(@PathVariable Long id, String name) {
        presentationService.updateName(id, name);

        return "redirect:/presentation";
    }

    @RequestMapping(value = "/presentation/{id}/presenter", method = RequestMethod.POST)
    public String postAddPresenterPresentationController(@PathVariable Long id, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();

        userPresentationService.save(user.getId(), id);

        return "redirect:/presentation";
    }

    @RequestMapping(value = "/presentation/{id}/delete", method = RequestMethod.POST)
    public String postDeletePresentationController(Model model, @PathVariable Long id) {
        presentationService.delete(id);

        return "redirect:/presentation";
    }
}
