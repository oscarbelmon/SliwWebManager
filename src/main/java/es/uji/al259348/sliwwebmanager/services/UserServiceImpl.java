package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.User;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.UserHighlightSearchResultMapper;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.UserRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean idExists(String id) {
        User user = userRepository.findOne(id);
        return user != null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findByNameWithHighlight(Pageable pageable, String filter) {
        return userRepository.findByNameWithHighlight(pageable, filter);
    }

}
