package pe.com.app.product.service;

import pe.com.app.product.model.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Interface</b>: ProductService <br/>
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
public interface ProductService {

    Flux<ProductDto> getProductsCatalog();

    Mono<ProductDto> getById(String id);
}
