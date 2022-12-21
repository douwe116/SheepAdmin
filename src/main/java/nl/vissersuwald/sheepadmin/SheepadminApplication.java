package nl.vissersuwald.sheepadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SheepadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheepadminApplication.class, args);
	}

}
