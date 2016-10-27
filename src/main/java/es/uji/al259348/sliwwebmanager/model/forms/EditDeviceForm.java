package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.hibernate.validator.constraints.NotBlank;

public class EditDeviceForm {

//    @NotBlank(message = "No puede estar vacío.")
    @NotBlank(message = "Can not be empty.")
    private String name;

    public EditDeviceForm() {

    }

    public EditDeviceForm(Device device) {
        this.name = device.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(Device device) {
        device.setName(name);
    }
}
