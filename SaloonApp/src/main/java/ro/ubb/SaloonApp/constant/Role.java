package ro.ubb.SaloonApp.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    CUSTOMER("customer"),
    EMPLOYEE("employee"),
    ADMIN("admin");

    public final String value;
}
