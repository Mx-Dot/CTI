package me.mxdot.cti.dto;

import java.util.List;

public record BoardSpecDto(
        List<LayerDto> layers,
        MaterialSpecDto materialSpec
) {
}
