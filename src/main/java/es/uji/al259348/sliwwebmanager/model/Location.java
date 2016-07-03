package es.uji.al259348.sliwwebmanager.model;

public class Location {

    private String name;
    private String configMsg;

    public Location() {

    }

    public Location(String name) {
        this.name = name;
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
        return "Location{" +
                "name='" + name + '\'' +
                ", configMsg='" + configMsg + '\'' +
                '}';
    }

}
