package me.mxdot.cti.model.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaterialSpec {

    @Column(nullable = false)
    private int gsm;

    @Column(nullable = false)
    private int gradeCode;

    @Override
    public String toString() {
        return gsm + "" + gradeCode;
    }
}
