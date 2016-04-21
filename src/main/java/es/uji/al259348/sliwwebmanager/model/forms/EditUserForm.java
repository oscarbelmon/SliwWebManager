package es.uji.al259348.sliwwebmanager.model.forms;

import es.uji.al259348.sliwwebmanager.model.User;
import org.hibernate.validator.constraints.NotBlank;

public class EditUserForm {

    @NotBlank(message = "No puede estar vacío.")
    private String name;

    public EditUserForm() {

    }

    public EditUserForm(User user) {
        this.name = user.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(User user) {
        user.setName(name);
    }
}
