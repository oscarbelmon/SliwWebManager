package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    boolean idExists(String id);

    User save(User user);
    User findOne(String id);

    Page<User> findAll(Pageable pageable);
    Page<User> findByNameWithHighlight(Pageable pageable, String filter);

}
