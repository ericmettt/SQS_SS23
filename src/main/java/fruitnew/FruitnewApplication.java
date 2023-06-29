package fruitnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FruitnewApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitnewApplication.class, args);
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> serverHeaderCustomizer() {
		return serverFactory -> serverFactory.setServerHeader("");
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> contentTypeOptionsCustomizer() {
		return serverFactory -> serverFactory.addInitializers(servletContext ->
			servletContext.setInitParameter("X-Content-Type-Options", "nosniff"));

	}

}
