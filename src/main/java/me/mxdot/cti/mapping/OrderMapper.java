package me.mxdot.cti.mapping;

import me.mxdot.cti.dto.BoardSpecDto;
import me.mxdot.cti.dto.CreateOrder;
import me.mxdot.cti.dto.LayerDto;
import me.mxdot.cti.dto.MaterialSpecDto;
import me.mxdot.cti.dto.OrderResponse;
import me.mxdot.cti.model.Order;
import me.mxdot.cti.model.value.BoardSpec;
import me.mxdot.cti.model.value.Layer;
import me.mxdot.cti.model.value.MaterialSpec;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public Order toEntity(CreateOrder request) {
        if (request == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderId(request.orderId());
        order.setBoardSpec(toBoardSpec(request.boardSpec()));
        order.setMeters(request.meters());
        return order;
    }

    public OrderResponse toResponse(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponse(
                order.getId(),
                order.getOrderId(),
                toBoardSpecDto(order.getBoardSpec()),
                order.getMeters()
        );
    }

    private BoardSpec toBoardSpec(BoardSpecDto dto) {
        if (dto == null) {
            return null;
        }

        return new BoardSpec(
                toLayers(dto.layers()),
                toMaterialSpec(dto.materialSpec())
        );
    }

    private BoardSpecDto toBoardSpecDto(BoardSpec boardSpec) {
        if (boardSpec == null) {
            return null;
        }

        return new BoardSpecDto(
                toLayerDtos(boardSpec.getLayers()),
                toMaterialSpecDto(boardSpec.getMaterialSpec())
        );
    }

    private List<Layer> toLayers(List<LayerDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toLayer)
                .toList();
    }

    private List<LayerDto> toLayerDtos(List<Layer> layers) {
        if (layers == null) {
            return null;
        }

        return layers.stream()
                .map(this::toLayerDto)
                .toList();
    }

    private Layer toLayer(LayerDto dto) {
        if (dto == null) {
            return null;
        }

        return new Layer(
                dto.type(),
                toMaterialSpec(dto.grade()),
                dto.fluteProfile()
        );
    }

    private LayerDto toLayerDto(Layer layer) {
        if (layer == null) {
            return null;
        }

        return new LayerDto(
                layer.getType(),
                toMaterialSpecDto(layer.getGrade()),
                layer.getFluteProfile()
        );
    }

    private MaterialSpec toMaterialSpec(MaterialSpecDto dto) {
        if (dto == null) {
            return null;
        }

        return new MaterialSpec(dto.gsm(), dto.gradeCode());
    }

    private MaterialSpecDto toMaterialSpecDto(MaterialSpec materialSpec) {
        if (materialSpec == null) {
            return null;
        }

        return new MaterialSpecDto(
                materialSpec.getGsm(),
                materialSpec.getGradeCode()
        );
    }
}
