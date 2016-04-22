package es.uji.al259348.sliwwebmanager.model.generators;

import es.uji.al259348.sliwwebmanager.model.Location;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LocationGenerator implements Generator<Location> {

    private final static String[] LOCATION_NAMES = {
            "Cocina", "Salón", "Baño", "Habitación", "Despacho", "Terraza"
    };

    private final static String[] CONFIG_MESSAGES = {
            "Ve a la cocina",
            "Siéntate en el sofá",
            "Ve al baño",
            "Túmbate en la cama",
            "Siéntate frente al ordenador",
            "Ve a la terraza"
    };

    @Override
    public Location generate() {

        Random random = new Random();

        int i = random.nextInt(LOCATION_NAMES.length);
        String name = LOCATION_NAMES[i];
        String configMsg = CONFIG_MESSAGES[i];

        Location location = new Location();
        location.setName(name);
        location.setConfigMsg(configMsg);

        return location;
    }

}
