package com.deizon.services.model.settings;

import com.deizon.services.model.data.DataField;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Settings extends DataField {

    private String id;
}
