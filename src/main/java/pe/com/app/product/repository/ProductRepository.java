package pe.com.app.product.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.app.product.model.persistence.ProductEntity;

/**
 * <b>Interface</b>: ProductRepository <br/>
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
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
}
