package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.repositories.DeviceRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
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
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public boolean idExists(String id) {
        Device device = deviceRepository.findOne(id);
        return device != null;
    }

    @Override
    public boolean macExists(String mac) {
        Device device = deviceRepository.findOneByMac(mac);
        return device != null;
    }

    public FacetedPage<Device> findHighlighted(String filter) {

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(new WildcardQueryBuilder("name", "*"+filter+"*"))
                .withHighlightFields(new HighlightBuilder.Field("name"))
                .build();

        FacetedPage<Device> page = elasticsearchTemplate.queryForPage(query, Device.class, new SearchResultMapper() {
            @Override
            public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

                List<Device> chunk = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    Device device = new Device();
                    device.setId(searchHit.getId());
                    device.setMac((String) searchHit.getSource().get("mac"));
                    device.setName(searchHit.getHighlightFields().get("name").fragments()[0].toString());
                    chunk.add(device);
                }

                if (chunk.size() > 0) {
                    return new FacetedPageImpl<T>((List<T>) chunk);
                }

                return new FacetedPageImpl<T>((List<T>) chunk);
            }
        });

        return page;
    }

}
