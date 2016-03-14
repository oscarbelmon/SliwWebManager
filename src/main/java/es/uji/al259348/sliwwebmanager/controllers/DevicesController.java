package es.uji.al259348.sliwwebmanager.controllers;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "devices")
public class DevicesController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping
    public String list(Model model) {
        Iterable<Device> devices = deviceService.findAll();
        model.addAttribute("devices", devices);
        return "devices/list";
    }

}
