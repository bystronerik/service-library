package com.deizon.services.model.settings;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetSettingsValueInput {

    private String settings;
    private String value;
}
