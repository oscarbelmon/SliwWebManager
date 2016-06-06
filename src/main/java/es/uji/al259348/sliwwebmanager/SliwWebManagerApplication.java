package es.uji.al259348.sliwwebmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "es.uji.al259348.sliwwebmanager.repositories.elasticsearch", considerNestedRepositories = true)
public class SliwWebManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SliwWebManagerApplication.class, args);
	}

}
