package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import es.uji.al259348.sliwwebmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends ElasticsearchCrudRepository<User, String>, UserRepositoryCustom {



}
