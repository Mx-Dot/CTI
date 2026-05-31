package me.mxdot.cti.dto;

public record CreateOrder(
        int orderId,
        BoardSpecDto boardSpec,
        int meters
) {
}
