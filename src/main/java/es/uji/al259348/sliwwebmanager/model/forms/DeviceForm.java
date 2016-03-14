package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.model.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceForm {

    @NotBlank
    private String id;
    @NotBlank
    private String mac;

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

    @Override
    public String toString() {
        return "DeviceForm{" +
                "id='" + id + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }

    public Device getDevice() {
        Device device = new Device();

        device.setId(this.getId());
        device.setMac(this.getMac());

        return device;
    }

}
