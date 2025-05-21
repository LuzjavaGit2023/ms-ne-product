package pe.com.app.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.app.product.common.mapper.ProductMapper;
import pe.com.app.product.common.util.Constant;
import pe.com.app.product.model.dto.ProductDto;
import pe.com.app.product.repository.ProductRepository;
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

    /**
     * This method is used to list products.
     *
     * @return Product Flux.
     */
    @Override
    public Flux<ProductDto> getProductsCatalog() {
        log.info("getProductsCatalog : execute");
        return productRepository.findAll()
                .map(ProductMapper::buildDto).log();
    }

    @Override
    public Mono<ProductDto> getById(String id) {
        log.info("getById : execute");
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalStateException(Constant.ELEMENT_NOT_FOUND)))
                .map(ProductMapper::buildDto).log();
    }
}
