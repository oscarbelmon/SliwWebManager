package es.uji.al259348.sliwwebmanager.model.generation;

import es.uji.al259348.sliwwebmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGenerator implements Generator<User> {

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    UserNameGenerator userNameGenerator;

    @Override
    public User generate() {
        String id = idGenerator.generate();
        String name = userNameGenerator.generate();

        User user = new User();
        user.setId(id);
        user.setName(name);

        return user;
    }

}
