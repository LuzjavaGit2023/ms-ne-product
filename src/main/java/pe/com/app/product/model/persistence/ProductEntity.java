package pe.com.app.product.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "products")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 7346145359381795413L;
    @Id
    private String id;
    private String productType;
    private String productSubType;
    private String label;
    private String description;
    private FeatureEntity feature;
}
