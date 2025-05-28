package pe.com.app.product.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductType {
    LIABILITY("Pasivo"),
    ASSET("Activo");

    private final String description;

    ProductType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static ProductType fromString(String value) {
        return value != null ? ProductType.valueOf(value.toUpperCase()) : null;
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
