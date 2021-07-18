package com.deizon.services.model.data.type;

import com.deizon.services.model.data.validator.LengthValidator;

public class LongTextType extends Type {

    static {
        validators.add(new LengthValidator(0, 65535));
    }
}
