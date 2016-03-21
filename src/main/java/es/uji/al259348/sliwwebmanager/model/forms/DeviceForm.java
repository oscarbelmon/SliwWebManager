package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.model.validation.annotations.DeviceIdAvailable;
import es.uji.al259348.sliwwebmanager.model.validation.annotations.DeviceMacAvailable;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

public class DeviceForm {

    @NotBlank(message = "No puede estar vacío.")
    @DeviceIdAvailable
    private String id;

    @NotBlank(message = "No puede estar vacío.")
    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$", message = "No es una dirección MAC válida.")
    @DeviceMacAvailable
    private String mac;

    @NotBlank(message = "No puede estar vacío.")
    private String name;

    public DeviceForm() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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
                ", mac='" + mac + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Device getDevice() {
        Device device = new Device();

        device.setId(this.getId());
        device.setMac(this.getMac());
        device.setName(this.getName());

        return device;
    }

}
