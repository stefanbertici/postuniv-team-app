package ro.ubb.SaloonApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "id_role")
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
        categories.add(category);
        category.getUsers().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getUsers().remove(this);
    }
}
