package pe.com.app.product.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovementDto implements Serializable {
    private Boolean unlimited;
    private Integer quantityMaxim;
    private Boolean specificDay;
}
