package me.mxdot.cti.repository;

import me.mxdot.cti.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByOrderId(int orderId);

    Optional<Order> findByOrderId(int orderId);
}
