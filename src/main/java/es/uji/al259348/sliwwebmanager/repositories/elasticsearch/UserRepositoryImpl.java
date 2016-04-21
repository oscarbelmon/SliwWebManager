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

    @Override
    public Page<User> findByNameWithHighlight(Pageable pageable, String filter) {

        String[] fields = new String[] { "name" };

        QueryBuilder queryBuilder = new MultiMatchQueryBuilder(filter, fields)
                .operator(MatchQueryBuilder.Operator.AND);

        HighlightBuilder.Field[] highlightFields = Arrays.stream(fields)
                .map(HighlightBuilder.Field::new)
                .toArray(HighlightBuilder.Field[]::new);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightFields(highlightFields)
                .build().setPageable(pageable);

        Page<User> page = elasticsearchTemplate.queryForPage(query, User.class, new UserHighlightSearchResultMapper());
        return page;

    }

}
