package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.model.validation.annotations.DeviceIdAvailable;
import org.hibernate.validator.constraints.NotBlank;

public class DeviceForm {

    @NotBlank
    @DeviceIdAvailable
    private String id;

    @NotBlank
    private String name;

    public DeviceForm() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceForm{" +
                "id='" + id + '\'' +
                '}';
    }

    public Device getDevice() {
        Device device = new Device();

        device.setId(this.getId());
        device.setName(this.getName());

        return device;
    }

}
