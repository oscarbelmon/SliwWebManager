package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.User;
import es.uji.al259348.sliwwebmanager.model.validation.annotations.UserIdAvailable;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class UserForm {

//    @NotBlank(message = "No puede estar vacío.")
    @NotBlank(message = "Can not be empty.")
    @UserIdAvailable
    private String id;
//    @NotBlank(message = "No puede estar vacío.")
    @NotBlank(message = "Can not be empty.")
    private String name;
    @Valid
//    @NotNull(message = "Debes añadir al menos 2 lugares.")
    @NotNull(message = "Add 2 places at least.")
//    @Size(min = 2, message = "Debes añadir al menos 2 lugares.")
    @Size(min = 2, message = "Add 2 places at least.")
    private List<LocationForm> locations;

    public UserForm() {

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

    public List<LocationForm> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationForm> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", locations=" + locations +
                '}';
    }

    public User getUser() {
        User user = new User();

        user.setId(this.getId());
        user.setName(this.getName());
        user.setLocations(this.getLocations().stream().map(LocationForm::getLocation).collect(Collectors.toList()));

        return user;
    }

}
