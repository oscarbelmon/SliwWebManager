package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import es.uji.al259348.sliwwebmanager.model.User;
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
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ElasticsearchQueryBuilder elasticsearchQueryBuilder;

    @Override
    public Page<User> findByNameWithHighlight(Pageable pageable, String filter) {

        String[] fields = new String[] { "name" };

        SearchQuery query = elasticsearchQueryBuilder.buildMultiMathQueryWithHighlightFields(filter, fields);
        query.setPageable(pageable);

        Page<User> page = elasticsearchTemplate.queryForPage(query, User.class, new UserHighlightSearchResultMapper());
        return page;

    }

}
