package com.deizon.services.model.file;

import com.deizon.services.model.Entity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class File extends Entity {

    private String directory;
    private String name;
    private String fileName;
    private FileType type;
}
