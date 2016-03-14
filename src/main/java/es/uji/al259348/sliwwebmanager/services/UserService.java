package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.User;

import java.util.List;

public interface UserService {

    Iterable<User> findAll();

}
