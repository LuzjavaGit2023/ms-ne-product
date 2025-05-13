package pe.com.app.producto.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovementEntity implements Serializable {
    private static final long serialVersionUID = 3873680094566412922L;
    private Boolean unlimited;
    private Integer quantityMaxim;
    private Boolean specificDay;
}
