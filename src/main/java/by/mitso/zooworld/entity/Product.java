package by.mitso.zooworld.entity;

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
@ToString
@EqualsAndHashCode(of = {"id"})
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


    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;


    public enum Availability {
        AVAILABLE,
        OUT_OF_STOCK,
        BACK_ORDER
    }

    public enum Category {
        CATS,
        DOGS,
        BIRDS,
        TURTLES
    }

    public enum Type {
        FOOD,
        TOY,
        ACCESSORIES
    }
}
