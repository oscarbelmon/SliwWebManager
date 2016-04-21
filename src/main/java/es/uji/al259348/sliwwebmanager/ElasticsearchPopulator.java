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

    private static final boolean POPULATE_ON_BOOT = false;
    private static final int NUM_USERS_TO_POPULATE = 128;
    private static final int NUM_DEVICES_TO_POPULATE = 256;

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

        if (POPULATE_ON_BOOT) {

            long userCount = userRepository.count();
            if (userCount == 0)
                populateUsers(NUM_USERS_TO_POPULATE);

            long deviceCount = deviceRepository.count();
            if (deviceCount == 0)
                populateDevices(NUM_DEVICES_TO_POPULATE);

        }

    }

    private void populateUsers(int numUsersToPopulate) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < numUsersToPopulate; i++) {
            users.add(userGenerator.generate());
        }
        userRepository.save(users);
    }

    private void populateDevices(int numDevicesToPopulate) {
        List<Device> devices = new ArrayList<>();
        for (int i = 0; i < numDevicesToPopulate; i++) {
            devices.add(deviceGenerator.generate());
        }
        deviceRepository.save(devices);
    }

}
