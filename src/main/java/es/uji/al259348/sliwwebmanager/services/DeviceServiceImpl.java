package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.DeviceHighlightSearchResultMapper;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.DeviceRepository;
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
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

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
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device findOne(String id) {
        return deviceRepository.findOne(id);
    }

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    @Override
    public Page<Device> findByMacAndNameWithHighlight(Pageable pageable, String filter) {
        return deviceRepository.findByMacAndNameWithHighlight(pageable, filter);
    }

}
