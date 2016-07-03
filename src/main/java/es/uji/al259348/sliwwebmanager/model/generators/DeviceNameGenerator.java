package es.uji.al259348.sliwwebmanager.model.generators;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DeviceNameGenerator implements Generator<String> {

    private final static String[] DEVICE_STATES = {
            "Viejo", "Nuevo", "Bonito", "Feo", "Potente"
    };

    private final static String[] DEVICE_TYPES = {
            "Smartphone", "Smartwatch", "Tablet", "Laptop", "Netbook", "Ultrabook"
    };

    private final static String[] DEVICE_OWNERS = {
            "Adrián", "Jose", "Maria", "Pilar", "Sergio", "David", "Luis", "David", "Carlos", "Lucia", "Carmen"
    };

    @Override
    public String generate() {

        Random random = new Random();

        String state = DEVICE_STATES[random.nextInt(DEVICE_STATES.length)];
        String type = DEVICE_TYPES[random.nextInt(DEVICE_TYPES.length)];
        String owner = DEVICE_OWNERS[random.nextInt(DEVICE_OWNERS.length)];

        return String.format("El %s %s de %s", state, type, owner);
    }

}
