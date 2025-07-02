package pe.com.app.product.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.app.product.model.dto.ProductDto;
import pe.com.app.product.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private ProductService service;
    private ProductDto request;

    @BeforeEach
    void setup() {
        request = ProductDto.builder().build();

    }

    @Test
    void givenGetAllRequest_whenGetAll_thenReturnSuccess() {
        when(service.getProductsCatalog()).thenReturn(Flux.just(request));
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductDto.class)
                .value(response -> {
                    Assertions.assertTrue(!response.isEmpty());
                });
    }

    @Test
    void givenGetByIdRequest_whenGetById_thenReturnSuccess() {
        when(service.getById(any())).thenReturn(Mono.just(request));
        webTestClient.get()
                .uri("/products/{id}", "id01row")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductDto.class);
    }
}
