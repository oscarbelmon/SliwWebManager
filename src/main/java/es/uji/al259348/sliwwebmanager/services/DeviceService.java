package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;

public interface DeviceService {

    Page<Device> findAll(Pageable pageable);
    FacetedPage<Device> findHighlighted(String filter);
    Device save(Device device);
    boolean idExists(String id);
    boolean macExists(String mac);

}
