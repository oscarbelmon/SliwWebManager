package es.uji.al259348.sliwwebmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @RequestMapping(path = "/login")
    public String login() {
        return "login";
    }

}
