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
public class MovementDto implements Serializable {
    private Boolean unlimited;
    private Integer quantityMaxim;
    private Boolean specificDay;
}
