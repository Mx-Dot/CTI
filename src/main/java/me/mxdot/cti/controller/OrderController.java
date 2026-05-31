package me.mxdot.cti.controller;

import me.mxdot.cti.dto.CreateOrder;
import me.mxdot.cti.dto.OrderResponse;
import me.mxdot.cti.mapping.OrderMapper;
import me.mxdot.cti.model.Order;
import me.mxdot.cti.model.value.BoardSpec;
import me.mxdot.cti.model.value.Layer;
import me.mxdot.cti.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderController(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrder request) {
        Order order = orderMapper.toEntity(request);
        validateOrder(order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @GetMapping
    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw badRequest("Request body is required.");
        }

        if (order.getOrderId() <= 0) {
            throw badRequest("orderId must be greater than zero.");
        }

        if (order.getMeters() <= 0) {
            throw badRequest("meters must be greater than zero.");
        }

        BoardSpec boardSpec = order.getBoardSpec();
        if (boardSpec == null) {
            throw badRequest("boardSpec is required.");
        }

        if (boardSpec.getMaterialSpec() == null) {
            throw badRequest("boardSpec.materialSpec is required.");
        }

        if (boardSpec.getLayers() == null || boardSpec.getLayers().isEmpty()) {
            throw badRequest("boardSpec.layers must contain at least one layer.");
        }

        for (int i = 0; i < boardSpec.getLayers().size(); i++) {
            Layer layer = boardSpec.getLayers().get(i);
            if (layer == null) {
                throw badRequest("boardSpec.layers[" + i + "] is required.");
            }
            if (layer.getType() == null) {
                throw badRequest("boardSpec.layers[" + i + "].type is required.");
            }
            if (layer.getGrade() == null) {
                throw badRequest("boardSpec.layers[" + i + "].grade is required.");
            }
        }
    }

    private ResponseStatusException badRequest(String reason) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, reason);
    }
}
