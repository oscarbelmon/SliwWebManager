package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import es.uji.al259348.sliwwebmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {

    Page<User> findByNameWithHighlight(Pageable pageable, String filter);

}
