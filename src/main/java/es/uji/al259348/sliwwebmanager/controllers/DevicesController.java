package es.uji.al259348.sliwwebmanager.controllers;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.model.forms.DeviceForm;
import es.uji.al259348.sliwwebmanager.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "devices")
public class DevicesController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping
    public String list(Model model,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size) {

        Page<Device> devicePage = deviceService.findAll(new PageRequest(page-1, size));

        model.addAttribute("devicePage", devicePage);

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
