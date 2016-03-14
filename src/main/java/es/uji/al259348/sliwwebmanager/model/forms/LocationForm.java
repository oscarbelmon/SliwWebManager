package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.Location;
import org.hibernate.validator.constraints.NotBlank;

public class LocationForm {

    @NotBlank
    private String name;
    @NotBlank
    private String configMsg;
    @NotBlank
    private String checkMsg;

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

    public String getCheckMsg() {
        return checkMsg;
    }

    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }

    @Override
    public String toString() {
        return "LocationForm{" +
                "name='" + name + '\'' +
                ", configMsg='" + configMsg + '\'' +
                ", checkMsg='" + checkMsg + '\'' +
                '}';
    }

    public Location getLocation() {
        Location location = new Location();

        location.setName(this.getName());
        location.setConfigMsg(this.getConfigMsg());
        location.setCheckMsg(this.getCheckMsg());

        return location;
    }

}
