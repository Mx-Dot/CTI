package me.mxdot.cti.dto;

import me.mxdot.cti.model.value.GradeType;

public record MaterialSpecDto(
        int gsm,
        GradeType gradeCode
) {
}
