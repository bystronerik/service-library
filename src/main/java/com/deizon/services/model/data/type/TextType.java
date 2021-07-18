package com.deizon.services.model.data.type;

import com.deizon.services.model.data.validator.LengthValidator;

public class TextType extends Type {

    static {
        validators.add(new LengthValidator(0, 255));
    }
}
