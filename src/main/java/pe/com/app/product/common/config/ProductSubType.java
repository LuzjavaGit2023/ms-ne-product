package pe.com.app.product.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductSubType {

    //Pasivo, cuentas bancarias
    PersonalLoan("CreditoPersonal"),
    BusinessLoan("CreditoEmpresarial"),
    CreditCard("TarjetaCredito"),

    //Activos, creditos
    SavingsAccount("CuentaAhorro"),
    CurrentAccount("CuentaCorriente"),
    FixedDeposit("PlazoFijo");

    private final String description;

    ProductSubType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static ProductSubType fromString(String value) {
        return value != null ? ProductSubType.valueOf(value.toUpperCase()) : null;
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}