package kuvaldis.food.core.domain;

import lombok.Data;

@Data
public class Volume {
    private final Double value;
    private final VolumeUnit unit;
}
