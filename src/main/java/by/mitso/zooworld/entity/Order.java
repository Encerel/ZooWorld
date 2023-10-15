package by.mitso.zooworld.entity;

//import jakarta.persistence.*;

import javax.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    //
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "ordered_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderAt;

    @Column(name = "completed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products;

    public enum OrderStatus {
        PENDING,
        COMPLETED,
        CANCELED,
        CONFIRMED
    }
}
