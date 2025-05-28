package pe.com.app.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "API Product", version = "1.0", description = "Reactive API for product management")
)
public class MicroServApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServApplication.class, args);
    }

}
