package pe.com.app.product.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LiabilityType {
    SavingsAccount("CuentaAhorro"),
    CurrentAccount("CuentaCorriente"),
    FixedDeposit("PlazoFijo"),
    ElectronicMoney("DineroElectronico");

    private final String description;

    LiabilityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static LiabilityType fromString(String value) {
        return value != null ? LiabilityType.valueOf(value.toUpperCase()) : null;
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
