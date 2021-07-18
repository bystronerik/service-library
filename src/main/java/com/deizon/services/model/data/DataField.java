package com.deizon.services.model.data;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DataField {

    private String name;
    private String description;
    private DataType type;
}
