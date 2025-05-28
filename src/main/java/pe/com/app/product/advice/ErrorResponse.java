package pe.com.app.product.advice;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 7794941563814018013L;
    private String error;
    private String message;
    private String timestamp;
}
