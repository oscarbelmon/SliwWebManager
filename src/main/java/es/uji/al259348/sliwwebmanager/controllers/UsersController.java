package es.uji.al259348.sliwwebmanager.controllers;

import es.uji.al259348.sliwwebmanager.model.User;
import es.uji.al259348.sliwwebmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "users")
public class UsersController {

    @Autowired
    UserService userService;

    @RequestMapping
    public String list(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

}
