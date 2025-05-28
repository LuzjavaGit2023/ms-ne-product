package pe.com.app.product.model.persistence;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommissionEntity implements Serializable {
    private static final long serialVersionUID = 534097346958778080L;
    private Boolean free;
    private Double cost;
}
