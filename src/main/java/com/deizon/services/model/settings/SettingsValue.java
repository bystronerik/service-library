package com.deizon.services.model.settings;

import com.deizon.services.model.Entity;
import com.deizon.services.model.data.DataType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collation = "settings")
public class SettingsValue extends Entity {

    private String settings;
    private String data;
    private DataType type;
}
