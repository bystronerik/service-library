package com.deizon.services.model.data.type;

import com.deizon.services.model.data.validator.RegexValidator;

public class TimeType extends Type {

    static {
        validators.add(new RegexValidator("^([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d$"));
    }
}
