package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import es.uji.al259348.sliwwebmanager.model.Device;
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
public class DeviceRepositoryImpl implements DeviceRepositoryCustom {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    ElasticsearchQueryBuilder elasticsearchQueryBuilder;

    @Override
    public Page<Device> findByMacAndNameWithHighlight(Pageable pageable, String filter) {

        String[] fields = new String[] { "name", "mac" };

        SearchQuery query = elasticsearchQueryBuilder.buildMultiMathQueryWithHighlightFields(filter, fields);
        query.setPageable(pageable);

        Page<Device> page = elasticsearchTemplate.queryForPage(query, Device.class, new DeviceHighlightSearchResultMapper());
        return page;

    }

}
