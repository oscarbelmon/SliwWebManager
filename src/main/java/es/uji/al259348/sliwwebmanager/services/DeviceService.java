package es.uji.al259348.sliwwebmanager.services;

import es.uji.al259348.sliwwebmanager.model.Device;

import java.util.List;

public interface DeviceService {

    Iterable<Device> findAll();
    Device save(Device device);

}
