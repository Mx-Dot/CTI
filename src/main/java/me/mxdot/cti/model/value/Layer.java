package me.mxdot.cti.model.value;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Layer {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LayerType type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "gsm", column = @Column(name = "grade_gsm", nullable = false)),
            @AttributeOverride(name = "gradeCode", column = @Column(name = "grade_code", nullable = false))
    })
    private MaterialSpec grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "flute_profile")
    private FluteProfile fluteProfile;

    @Override
    public String toString() {
        return type + " " + grade + " " + fluteProfile;
    }
}
