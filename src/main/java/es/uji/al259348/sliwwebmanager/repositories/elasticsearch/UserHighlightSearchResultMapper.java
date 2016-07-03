package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import es.uji.al259348.sliwwebmanager.model.User;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.SearchResultMapper;

import java.util.ArrayList;
import java.util.List;

public class UserHighlightSearchResultMapper implements SearchResultMapper {

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

}
