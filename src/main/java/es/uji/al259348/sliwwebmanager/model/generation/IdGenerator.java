package es.uji.al259348.sliwwebmanager.model.generation;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdGenerator implements Generator<String> {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

}
