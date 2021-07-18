package com.deizon.services.model.data;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DataField {

    private String name;
    private String description;
    private DataType type;
}
