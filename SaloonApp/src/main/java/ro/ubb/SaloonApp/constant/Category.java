package ro.ubb.SaloonApp.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Category {

    MANICURE("manicure"),
    PEDICURE("pedicure"),
    MAKEUP("makeup"),
    HAIRCUT_WOMAN("women's haircut"),
    HAIRCUT_MAN("men's haircut"),
    BEARD("beard");

    public final String value;
}
