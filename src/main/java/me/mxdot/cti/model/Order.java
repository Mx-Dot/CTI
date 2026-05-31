package me.mxdot.cti.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.mxdot.cti.model.value.BoardSpec;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "order_number", nullable = false, unique = true)
    private int orderId;

    @Setter
    @Embedded
    private BoardSpec boardSpec;

    @Setter
    @Column(nullable = false)
    private int meters;
}
