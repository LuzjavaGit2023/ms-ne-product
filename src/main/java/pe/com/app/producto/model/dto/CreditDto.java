package pe.com.app.producto.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.producto.model.persistence.CreditEntity;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreditDto implements Serializable {
    private Boolean unlimited;
    private Integer quantityMaxim;
}
