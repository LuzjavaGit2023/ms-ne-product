package pe.com.app.product.model.persistence;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeatureEntity implements Serializable {
    private static final long serialVersionUID = 8909396096289476165L;
    private CommissionEntity commission;
    private MovementEntity movement;
    private CreditEntity credit;
}
