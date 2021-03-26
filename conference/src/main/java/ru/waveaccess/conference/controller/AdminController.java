package ru.waveaccess.conference.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.waveaccess.conference.model.enums.Role;
import ru.waveaccess.conference.model.form.RoleForm;
import ru.waveaccess.conference.service.interfaces.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsersController(Model model, Authentication authentication) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", Stream.of(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
        model.addAttribute("RoleForm", new RoleForm());
        return "admin";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String postUsersController() {

        return "redirect:/admin";
    }

    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.POST)
    public String postUsersUpdateNameController(String name, @PathVariable Long id) {
        userService.updateName(name, id);

        return "redirect:/users";
    }

    @RequestMapping(value = "/users/role/{id}", method = RequestMethod.POST)
    public String postUsersUpdateRoleController(@Valid RoleForm role, @PathVariable Long id) {
        userService.updateRole(role.getRole(), id);

        return "redirect:/users";
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.POST)
    public String postUsersDeleteController(Model model, Authentication authentication, @PathVariable Long id) {
        userService.delete(id);

        return "redirect:/users";
    }
}
