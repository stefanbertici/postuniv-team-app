package ro.ubb.SaloonApp.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import jakarta.persistence.*;
import lombok.*;
import ro.ubb.SaloonApp.constant.Status;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "beautyservice_id")
    private BeautyService beautyService;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "res_date")
    private LocalDate resDate;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @Column(name = "res_hour")
    private LocalTime resHour;
}
