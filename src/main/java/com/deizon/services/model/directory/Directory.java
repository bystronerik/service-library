package com.deizon.services.model.directory;

import com.deizon.services.model.Entity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Directory extends Entity {

    private String name;
    private String parent;
}
