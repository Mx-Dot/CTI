package me.mxdot.cti.dto;

import me.mxdot.cti.model.value.FluteProfile;
import me.mxdot.cti.model.value.LayerType;

public record LayerDto(
        LayerType type,
        MaterialSpecDto grade,
        FluteProfile fluteProfile
) {
}
