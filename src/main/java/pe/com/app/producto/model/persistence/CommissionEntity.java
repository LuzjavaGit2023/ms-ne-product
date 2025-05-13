package pe.com.app.producto.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommissionEntity implements Serializable {
    private static final long serialVersionUID = 534097346958778080L;
    private Boolean free;
    private Double cost;
}
