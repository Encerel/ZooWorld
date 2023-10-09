package by.mitso.zooworld.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    public enum Availability {
        AVAILABLE,
        OUT_OF_STOCK,
        BACK_ORDER
    }
}
