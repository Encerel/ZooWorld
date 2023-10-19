package by.mitso.zooworld.entity;

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
@ToString
@EqualsAndHashCode(of = {"id"})
public class Cart {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> items;
}
