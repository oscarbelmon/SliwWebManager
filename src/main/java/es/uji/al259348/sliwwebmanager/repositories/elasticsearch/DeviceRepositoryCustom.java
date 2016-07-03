package es.uji.al259348.sliwwebmanager.repositories.elasticsearch;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceRepositoryCustom {

    Page<Device> findByMacAndNameWithHighlight(Pageable pageable, String filter);

}
