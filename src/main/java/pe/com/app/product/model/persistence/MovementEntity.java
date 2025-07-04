package pe.com.app.product.model.persistence;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovementEntity implements Serializable {
    private static final long serialVersionUID = 3873680094566412922L;
    private Boolean unlimited;
    private Integer quantityMaxim;
    private Boolean specificDay;
    private Double cost;
}
