package pe.com.app.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.app.product.advice.ErrorResponse;
import pe.com.app.product.model.dto.ProductDto;
import pe.com.app.product.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ProductControl <br/>
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Products", description = "Functional operations related to products")
public class ProductController {

    private final ProductService productService;

    /**
     * This method is used to list products.
     *
     * @return Product Flux.
     */
    @GetMapping
    @Operation(summary = "This method is used to list products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class)
            )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Flux<ProductDto> getAllProducts() {
        return productService.getProductsCatalog();
    }


    /**
     * This method is used to get element.
     *
     * @return ProductDto Mono.
     */
    @GetMapping("/{id}")
    @Operation(summary = "This method is used to get element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<ProductDto> getProduct(@PathVariable String id) {
        return productService.getById(id);
    }
}
