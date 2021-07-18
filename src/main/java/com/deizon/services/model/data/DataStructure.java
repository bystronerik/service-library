package com.deizon.services.model.data;

import com.deizon.services.model.Entity;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DataStructure extends Entity {

    private String id;
    private String name;
    private String description;
    private List<? extends DataField> fields;
}
