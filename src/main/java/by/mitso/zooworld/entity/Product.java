package by.mitso.zooworld.entity;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @Column(name = "availability")
    @Enumerated(EnumType.STRING)
    private Availability availability;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_item",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @ToString.Exclude
    private List<Order> orders;

    @ToString.Exclude
    @ManyToMany(mappedBy = "products")
    private List<Cart> cart;

    public enum Availability {
        AVAILABLE,
        OUT_OF_STOCK,
        BACK_ORDER
    }
}
