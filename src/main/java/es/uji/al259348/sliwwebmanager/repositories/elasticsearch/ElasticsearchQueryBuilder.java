package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ElasticsearchQueryBuilder {

    SearchQuery buildMultiMathQueryWithHighlightFields(String filter, String... fields) {

        QueryBuilder queryBuilder = new MultiMatchQueryBuilder(filter, fields)
                .operator(MatchQueryBuilder.Operator.AND);

        HighlightBuilder.Field[] highlightFields = Arrays.stream(fields)
                .map(HighlightBuilder.Field::new)
                .toArray(HighlightBuilder.Field[]::new);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightFields(highlightFields)
                .build();

        return query;
    }

}
