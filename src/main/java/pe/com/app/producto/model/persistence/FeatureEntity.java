package pe.com.app.producto.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeatureEntity implements Serializable {
    private static final long serialVersionUID = 8909396096289476165L;
    private CommissionEntity commission;
    private MovementEntity movement;
    private CreditEntity credit;
}
