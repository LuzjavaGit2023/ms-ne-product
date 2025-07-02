package pe.com.app.product.repository.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.data.redis")
@Data
public class RedisPropertiesConfig {
    private String host;
    private int port;
    private String password;
    private long timeout;
}
