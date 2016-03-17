package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceService {

    Page<Device> findAll(Pageable pageable);
    Device save(Device device);
    boolean idExists(String id);

}
