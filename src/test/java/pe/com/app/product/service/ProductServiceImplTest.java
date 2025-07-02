package pe.com.app.product.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.app.product.common.util.Constant;
import pe.com.app.product.model.dto.CreditDto;
import pe.com.app.product.model.dto.FeatureDto;
import pe.com.app.product.model.dto.ProductDto;
import pe.com.app.product.model.persistence.*;
import pe.com.app.product.repository.ProductRepository;
import pe.com.app.product.repository.cache.ProductRedisService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductRedisService cache;

    private ProductDto request;
    private ProductEntity entity;

    @BeforeEach
    void setup() {
        request = ProductDto.builder().build();
        entity = buildEntityCredit("idxx1");
    }

    @Test
    void givenGetAllRequest_whenGetAllFromDataBase_thenReturnSuccess() {
        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(cache.getList()).thenReturn(Mono.empty());
        when(cache.setList(any())).thenReturn(Mono.just(true));

        var result = service.getProductsCatalog();
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof ProductDto);
                })
                .verifyComplete();
    }

    @Test
    void givenGetAllRequest_whenGetAllFromRedis_thenReturnSuccess() {
        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(cache.getList()).thenReturn(Mono.just(List.of(buildProductDto())));

        var result = service.getProductsCatalog();
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof ProductDto);
                })
                .verifyComplete();
    }

    @Test
    void givenGetAllRequest_whenRedisDisabledGoDataBase_thenReturnSuccess() {
        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(cache.getList()).thenReturn(Mono.error(new RuntimeException("Redis unable")));

        var result = service.getProductsCatalog();
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof ProductDto);
                })
                .verifyComplete();
    }

    @Test
    void givenGetAllRequest_whenOkDataBaseButIsEmpty_thenReturnSuccess() {
        when(repository.findAll()).thenReturn(Flux.empty());
        when(cache.getList()).thenReturn(Mono.empty());

        var result = service.getProductsCatalog();
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void givenGetByIdRequest_whenGetById_thenReturnSuccess() {
        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(cache.getList()).thenReturn(Mono.empty());
        when(cache.setList(any())).thenReturn(Mono.just(true));

        var result = service.getById("idxx1");
        StepVerifier.create(result)
                .assertNext(e -> assertTrue(e instanceof ProductDto))
                .verifyComplete();
    }

    @Test
    void givenGetByIdRequest_whenGetById_thenReturnError() {
        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(cache.getList()).thenReturn(Mono.empty());
        when(cache.setList(any())).thenReturn(Mono.just(true));

        var result = service.getById("idxx1NotFound");
        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof IllegalStateException);
                    assertEquals(Constant.ELEMENT_NOT_FOUND, error.getMessage());
                })
                .verify();
    }

    private ProductEntity buildEntityCredit(String id) {
        var entity = new ProductEntity();
        entity.setId(id);
        var feature = new FeatureEntity();
        var credit = new CreditEntity();
        entity.setFeature(feature);
        entity.getFeature().setCredit(credit);
        entity.getFeature().setMovement(new MovementEntity());
        entity.getFeature().setCommission(new CommissionEntity());
        return entity;
    }

    private ProductDto buildProductDto() {
        return ProductDto.builder()
                .id("111")
                .description("TXT")
                .productType("Test")
                .productSubType("TT")
                .label("lab")
                .build();
    }
}
