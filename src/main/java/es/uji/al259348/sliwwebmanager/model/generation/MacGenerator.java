package es.uji.al259348.sliwwebmanager.model.generation;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MacGenerator implements Generator<String> {

    @Override
    public String generate() {

        Random random = new Random();

        byte[] bytes = new byte[6];
        random.nextBytes(bytes);

        StringBuilder sb = new StringBuilder(17);

        for (byte b : bytes) {

            if(sb.length() > 0)
                sb.append(":");

            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
