package pe.com.app.product.model.persistence;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditEntity implements Serializable {
    private static final long serialVersionUID = -7363006836523138467L;
    private Boolean unlimited;
    private Integer quantityMaxim;
}
