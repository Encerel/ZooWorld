package by.mitso.zooworld.entity;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Cart {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
}
