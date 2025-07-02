package pe.com.app.product.common.mapper;

import pe.com.app.product.model.dto.CommissionDto;
import pe.com.app.product.model.dto.CreditDto;
import pe.com.app.product.model.dto.FeatureDto;
import pe.com.app.product.model.dto.MovementDto;
import pe.com.app.product.model.dto.ProductDto;
import pe.com.app.product.model.persistence.ProductEntity;

public class ProductMapper {

    public static ProductDto buildDto(ProductEntity e) {
        final var product = ProductDto.builder()
                .id(e.getId())
                .productType(e.getProductType())
                .productSubType(e.getProductSubType())
                .label(e.getLabel())
                .description(e.getDescription())
                .feature(FeatureDto.builder().build())
                .build();

        if (e.getFeature() != null) {
            if (e.getFeature().getCommission() != null) {
                product.getFeature().setCommission(
                        CommissionDto.builder()
                        .cost(e.getFeature().getCommission().getCost())
                        .free(e.getFeature().getCommission().getFree())
                        .build()
                );
            }

            if (e.getFeature().getMovement() != null) {
                product.getFeature().setMovement(
                        MovementDto.builder()
                                .unlimited(e.getFeature().getMovement().getUnlimited())
                                .quantityMaxim(e.getFeature().getMovement().getQuantityMaxim())
                                .specificDay(e.getFeature().getMovement().getSpecificDay())
                                .cost(e.getFeature().getMovement().getCost())
                                .build()
                );
            }
            if (e.getFeature().getCredit() != null) {
                product.getFeature().setCredit(
                        CreditDto.builder()
                                .unlimited(e.getFeature().getCredit().getUnlimited())
                                .quantityMaxim(e.getFeature().getCredit().getQuantityMaxim())
                                .build()
                );
            }
        }
        return product;
    }
}
