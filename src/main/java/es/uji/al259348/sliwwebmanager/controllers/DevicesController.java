package es.uji.al259348.sliwwebmanager.controllers;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.model.forms.DeviceForm;
import es.uji.al259348.sliwwebmanager.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

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

    @RequestMapping(path = "create")
    public String create(Model model) {

        DeviceForm deviceForm = new DeviceForm();

        model.addAttribute("deviceForm", deviceForm);

        return "devices/create";
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public String create(@Valid DeviceForm deviceForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "devices/create";
        }

        Device device = deviceForm.getDevice();
        System.out.println(device);

        deviceService.save(device);

        return "redirect:/devices";
    }


}
