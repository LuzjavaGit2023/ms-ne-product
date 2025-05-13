package pe.com.app.producto.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FeatureDto implements Serializable {
    private CommissionDto commission;
    private MovementDto movement;
    private CreditDto credit;
}
