package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.SearchResultMapper;

import java.util.ArrayList;
import java.util.List;

public class DeviceHighlightSearchResultMapper implements SearchResultMapper {

    @Override
    public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

        List<Device> chunk = new ArrayList<>();
        for (SearchHit searchHit : response.getHits()) {

            String sourceName = searchHit.getSource().get("name").toString();
            String sourceMac = searchHit.getSource().get("mac").toString();

            HighlightField highlightName = searchHit.getHighlightFields().get("name");
            HighlightField highlightMac = searchHit.getHighlightFields().get("mac");

            String name = (highlightName != null) ? highlightName.fragments()[0].toString() : sourceName;
            String mac = (highlightMac != null) ? highlightMac.fragments()[0].toString() : sourceMac;

            Device device = new Device();
            device.setId(searchHit.getId());
            device.setMac(mac);
            device.setName(name);
            chunk.add(device);
        }

        List<T> content = (List<T>) chunk;
        long total = response.getHits().getTotalHits();
        return new FacetedPageImpl<>(content, pageable, total);

    }

}
