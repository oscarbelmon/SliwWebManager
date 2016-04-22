package es.uji.al259348.sliwwebmanager.model.generators;

import es.uji.al259348.sliwwebmanager.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LocationListGenerator implements Generator<List<Location>> {

    @Autowired
    LocationGenerator locationGenerator;

    @Override
    public List<Location> generate() {

        Random random = new Random();

        int min = 2;
        int max = 6;
        int n = min + random.nextInt(max-min+1);

        Set<String> locationNames = new HashSet<>();
        List<Location> locations = new ArrayList<>();

        while (locations.size() < n) {
            Location location = locationGenerator.generate();
            if (!locationNames.contains(location.getName())) {
                locationNames.add(location.getName());
                locations.add(location);
            }
        }

        return locations;
    }

}
