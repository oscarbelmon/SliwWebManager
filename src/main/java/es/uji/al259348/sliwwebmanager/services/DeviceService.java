package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceService {

    boolean idExists(String id);
    boolean macExists(String mac);

    Device save(Device device);
    Device findOne(String id);

    Page<Device> findAll(Pageable pageable);
    Page<Device> findHighlighted(Pageable pageable, String filter);

}
