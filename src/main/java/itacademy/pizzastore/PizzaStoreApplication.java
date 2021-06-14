package itacademy.pizzastore;

import itacademy.pizzastore.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
public class PizzaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaStoreApplication.class, args);
	}

}
