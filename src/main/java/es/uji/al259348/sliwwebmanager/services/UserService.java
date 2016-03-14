package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.User;

public interface UserService {

    Iterable<User> findAll();
    User save(User user);

}
