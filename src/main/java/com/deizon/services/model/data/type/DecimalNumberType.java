package com.deizon.services.model.data.type;

import com.deizon.services.model.data.validator.RegexValidator;

public class DecimalNumberType extends Type {

    static {
        validators.add(new RegexValidator("^\\d+(\\.|\\,)\\d+$"));
    }
}
