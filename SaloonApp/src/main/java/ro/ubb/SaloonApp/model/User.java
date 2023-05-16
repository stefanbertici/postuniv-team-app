package ro.ubb.SaloonApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.SaloonApp.constant.Role;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_category",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        if (Objects.isNull(this.categories)) {
            this.categories = new HashSet<>();
        }

        categories.add(category);
        category.getUsers().add(this);
    }

    public void removeCategory(Category category) {
        if (Objects.isNull(this.categories)) {
            this.categories = new HashSet<>();
        }

        categories.remove(category);
        category.getUsers().remove(this);
    }
}
