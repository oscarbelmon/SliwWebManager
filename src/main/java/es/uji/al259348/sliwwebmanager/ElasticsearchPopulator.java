package es.uji.al259348.sliwwebmanager;

import es.uji.al259348.sliwwebmanager.model.Device;
import es.uji.al259348.sliwwebmanager.model.User;
import es.uji.al259348.sliwwebmanager.model.generators.DeviceGenerator;
import es.uji.al259348.sliwwebmanager.model.generators.UserGenerator;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.DeviceRepository;
import es.uji.al259348.sliwwebmanager.repositories.elasticsearch.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ElasticsearchPopulator {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    UserGenerator userGenerator;

    @Autowired
    DeviceGenerator deviceGenerator;

    @PostConstruct
    public void init() {

        long userCount = userRepository.count();
        if (userCount == 0) {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 128; i++) {
                users.add(userGenerator.generate());
            }
            userRepository.save(users);
        }

        long deviceCount = deviceRepository.count();
        if (deviceCount == 0) {
            List<Device> devices = new ArrayList<>();
            for (int i = 0; i < 256; i++) {
                devices.add(deviceGenerator.generate());
            }
            deviceRepository.save(devices);
        }

    }

}
