package pe.com.app.product.repository.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import pe.com.app.product.model.dto.ProductDto;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ProductListRedisService <br/>
 * <b>Copyright</b>: 2025 Tu Banco - Celula <br/>
 * .
 *
 * @author 2025 Tu Banco - Peru <br/>
 * <u>Service Provider</u>: Tu Banco <br/>
 * <u>Changes:</u><br/>
 * <ul>
 * <li>
 * May 10, 2025 Creación de Clase.
 * </li>
 * </ul>
 */
@Service
@Slf4j
public class ProductRedisService {
    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    private static final String KEY = "LIST_PRODUCT_REDIS";

    public ProductRedisService(ReactiveRedisTemplate<String, String> reactiveStringRedisTemplate,
                               ObjectMapper objectMapper) {
        this.redisTemplate = reactiveStringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    public Mono<Boolean> setList(List<ProductDto> products) {
        try {
            final String json = objectMapper.writeValueAsString(products);
            return redisTemplate.opsForValue().set(KEY, json, Duration.ofMinutes(10));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }

    public Mono<List<ProductDto>> getList() {
        return redisTemplate.opsForValue().get(KEY)
                .flatMap(json -> {
                    try {
                        final List<ProductDto> personas =
                                objectMapper.readValue(json, new TypeReference<>() { }
                        );
                        log.info("List found on REDIS");
                        return Mono.just(personas);
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage(), e);
                        return Mono.error(e);
                    }
                });
    }

    public Mono<Void> evictCache() {
        return redisTemplate.delete(KEY).then();
    }
}
