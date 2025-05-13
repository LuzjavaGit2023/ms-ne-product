package pe.com.app.producto.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditEntity implements Serializable {
    private static final long serialVersionUID = -7363006836523138467L;
    private Boolean unlimited;
    private Integer quantityMaxim;
}
