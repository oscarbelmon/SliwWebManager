package es.uji.al259348.sliwwebmanager.model.generators;

import es.uji.al259348.sliwwebmanager.model.Location;
import es.uji.al259348.sliwwebmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGenerator implements Generator<User> {

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    UserNameGenerator userNameGenerator;

    @Autowired
    LocationListGenerator locationListGenerator;

    @Override
    public User generate() {
        String id = idGenerator.generate();
        String name = userNameGenerator.generate();
        List<Location> locations = locationListGenerator.generate();

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLocations(locations);

        return user;
    }

}
