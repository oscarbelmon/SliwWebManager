package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.Location;
import org.hibernate.validator.constraints.NotBlank;

public class LocationForm {

    @NotBlank(message = "No puede estar vacío.")
    private String name;
    @NotBlank(message = "No puede estar vacío.")
    private String configMsg;

    public LocationForm() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigMsg() {
        return configMsg;
    }

    public void setConfigMsg(String configMsg) {
        this.configMsg = configMsg;
    }

    @Override
    public String toString() {
        return "LocationForm{" +
                "name='" + name + '\'' +
                ", configMsg='" + configMsg + '\'' +
                '}';
    }

    public Location getLocation() {
        Location location = new Location();

        location.setName(this.getName());
        location.setConfigMsg(this.getConfigMsg());

        return location;
    }

}
