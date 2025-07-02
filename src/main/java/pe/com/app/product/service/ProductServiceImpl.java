package pe.com.app.product.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.app.product.common.mapper.ProductMapper;
import pe.com.app.product.common.util.Constant;
import pe.com.app.product.model.dto.ProductDto;
import pe.com.app.product.repository.ProductRepository;
import pe.com.app.product.repository.cache.ProductRedisService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ProductServiceImpl <br/>
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
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductRedisService cache;

    /**
     * This method is used to list products.
     *
     * @return Product Flux.
     */
    @Override
    public Flux<ProductDto> getProductsCatalog() {
        log.info("getProductsCatalog : execute");
        return cache.getList()
                .switchIfEmpty(getProductsCatalogOnDatabase(true))
                .onErrorResume(error -> {
                    log.info("Error en Redis, {}", error.getMessage());
                    return getProductsCatalogOnDatabase(false);
                })
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<ProductDto> getById(String id) {
        log.info("getById : execute");
        return getProductsCatalog()
                .filter(productDto -> productDto.getId().equals(id))
                .switchIfEmpty(Mono.error(new IllegalStateException(Constant.ELEMENT_NOT_FOUND)))
                .next().log();
    }

    public Mono<List<ProductDto>> getProductsCatalogOnDatabase(boolean saveRedis) {
        return productRepository.findAll()
                .map(ProductMapper::buildDto)
                .collectList()
                .flatMap(list -> {
                    log.info("Lista de productos consultados en BD");
                    if (saveRedis) {
                        if (list.isEmpty()) {
                            log.info("Lista vacia, no lo guardamos en cache");
                            return Mono.just(list);
                        }
                        log.info("Se procede a grabar en REDIS, cantidad : {}", list.size());
                        return cache.setList(list).thenReturn(list);
                    }
                    return Mono.just(list);
                });
    }
}
