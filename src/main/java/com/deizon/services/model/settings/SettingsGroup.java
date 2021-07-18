package com.deizon.services.model.settings;

import com.deizon.services.model.Entity;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SettingsGroup extends Entity {

    private String name;
    private String description;
    private String parent;
    private List<Settings> settings;
}
