package ro.ubb.SaloonApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @Column(name = "encrypted_password")
    private String encryptedPassword;
    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;
}
