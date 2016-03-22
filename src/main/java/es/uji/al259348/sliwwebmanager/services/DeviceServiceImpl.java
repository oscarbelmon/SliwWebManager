package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.model.generation.DeviceGenerator;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.DeviceRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {

    @PostConstruct
    public void init() {
        for (int i = 0; i < 50; i++) {
            Device device = deviceGenerator.generate();
            deviceRepository.save(device);
        }
    }

    @Autowired
    DeviceGenerator deviceGenerator;

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

    @Override
    public Page<Device> findHighlighted(Pageable pageable, String filter) {

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(
                        new MultiMatchQueryBuilder(filter, "name", "mac")
                                .operator(MatchQueryBuilder.Operator.AND)
                )
                .withHighlightFields(
                        new HighlightBuilder.Field("name"),
                        new HighlightBuilder.Field("mac")
                )
                .build().setPageable(pageable);

        Page<Device> page = elasticsearchTemplate.queryForPage(query, Device.class, new SearchResultMapper() {
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
        });

        return page;
    }

}
