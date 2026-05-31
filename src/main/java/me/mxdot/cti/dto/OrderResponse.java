package me.mxdot.cti.dto;

public record OrderResponse(
        Long id,
        int orderId,
        BoardSpecDto boardSpec,
        int meters
) {
}
