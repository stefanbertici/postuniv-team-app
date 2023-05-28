package ro.ubb.SaloonApp.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Availability {

    DEFAULT_START_HOUR(9),

    DEFAULT_START_MINUTE(0),
    DEFAULT_AVAILABILITY_BLOCKS(16);

    public final int value;
}
