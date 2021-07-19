package com.deizon.services.model.settings;

import com.deizon.services.model.FindInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FindSettingsGroupInput extends FindInput {
    private String name;
    private String parent;
}
