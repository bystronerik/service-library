package com.deizon.services.model.settings;

import com.deizon.services.model.Entity;
import com.deizon.services.model.data.DataType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SettingsValue extends Entity {

    private String settings;
    private String data;
    private DataType type;
}
