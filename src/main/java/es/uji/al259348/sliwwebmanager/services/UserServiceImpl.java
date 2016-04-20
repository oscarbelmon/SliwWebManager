package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.User;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.UserRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

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
    public Page<User> findHighlighted(Pageable pageable, String filter) {

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(
                        new MultiMatchQueryBuilder(filter, "name")
                                .operator(MatchQueryBuilder.Operator.AND)
                )
                .withHighlightFields(
                        new HighlightBuilder.Field("name")
                )
                .build().setPageable(pageable);

        Page<User> page = elasticsearchTemplate.queryForPage(query, User.class, new SearchResultMapper() {
            @Override
            public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

                List<User> chunk = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {

                    String sourceName = searchHit.getSource().get("name").toString();

                    HighlightField highlightName = searchHit.getHighlightFields().get("name");

                    String name = (highlightName != null) ? highlightName.fragments()[0].toString() : sourceName;

                    User user = new User();
                    user.setId(searchHit.getId());
                    user.setName(name);
                    chunk.add(user);
                }

                List<T> content = (List<T>) chunk;
                long total = response.getHits().getTotalHits();
                return new FacetedPageImpl<>(content, pageable, total);
            }
        });

        return page;
    }

}
