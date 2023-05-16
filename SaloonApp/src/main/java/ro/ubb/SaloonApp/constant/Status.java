package ro.ubb.SaloonApp.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {

    PENDING("pending"),
    MODIFIED("modified"),
    ACCEPTED("accepted"),
    FINISHED("completed");

    public final String value;
}
