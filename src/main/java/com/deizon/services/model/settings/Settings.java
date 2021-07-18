package com.deizon.services.model.settings;

import com.deizon.services.model.data.DataField;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Settings extends DataField {

    private String id;
}
