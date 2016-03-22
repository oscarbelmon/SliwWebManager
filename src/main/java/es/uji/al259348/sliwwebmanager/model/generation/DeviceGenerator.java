package es.uji.al259348.sliwwebmanager.model.generation;

import es.uji.al259348.sliwwebmanager.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceGenerator implements Generator<Device> {

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    MacGenerator macGenerator;

    @Autowired
    DeviceNameGenerator deviceNameGenerator;

    @Override
    public Device generate() {
        String id = idGenerator.generate();
        String mac = macGenerator.generate();
        String name = deviceNameGenerator.generate();

        Device device = new Device();
        device.setId(id);
        device.setMac(mac);
        device.setName(name);

        return device;
    }
}
