package com.deizon.services.model.settings;

import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SettingsGroup {

    private String id;
    private String name;
    private String description;
    private String parent;
    private List<Settings> settings;
}
