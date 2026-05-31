package me.mxdot.cti.model.value;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSpec {

    @ElementCollection
    @CollectionTable(
            name = "board_layers",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @OrderColumn(name = "layer_index")
    private List<Layer> layers;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "gsm", column = @Column(name = "material_gsm", nullable = false)),
            @AttributeOverride(name = "gradeCode", column = @Column(name = "material_grade_code", nullable = false))
    })
    private MaterialSpec materialSpec;
}
