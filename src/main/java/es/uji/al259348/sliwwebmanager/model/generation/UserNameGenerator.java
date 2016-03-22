package es.uji.al259348.sliwwebmanager.model.generation;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserNameGenerator implements Generator<String> {

    private final static String[] FIRST_NAMES = {
            "Adrián", "Jose", "Maria", "Pilar", "Sergio", "David", "Luis", "David", "Carlos", "Lucia", "Carmen", "Paula"
    };

    private final static String[] LAST_NAMES = {
            "López", "Martínez", "García", "Muñoz", "Ruíz", "Sánchez", "Gil", "Castro", "Puertas", "Cabedo", "Escrig"
    };

    @Override
    public String generate() {
        Random random = new Random();

        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String lastName2 = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        return String.format("%s %s %s", firstName, lastName, lastName2);
    }

}
