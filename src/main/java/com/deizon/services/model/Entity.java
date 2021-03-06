package com.deizon.services.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Entity {

    @Id private String id;
    private String clientId;

    private Boolean deleted;
    private Instant deleteDate;

    private Instant createDate;
    private Instant updateDate;
}
