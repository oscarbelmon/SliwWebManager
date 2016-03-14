package es.uji.al259348.sliwwebmanager.repositories;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends ElasticsearchCrudRepository<Device, String> {



}
