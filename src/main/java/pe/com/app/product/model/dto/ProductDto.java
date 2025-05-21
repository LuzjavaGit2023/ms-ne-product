package pe.com.app.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto implements Serializable {
    private String id;
    private String productType;
    private String productSubType;
    private String label;
    private String description;
    private FeatureDto feature;
}
