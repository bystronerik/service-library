package com.deizon.services.model.data.type;

import com.deizon.services.model.data.validator.RegexValidator;

public class DateType extends Type {

    static {
        validators.add(new RegexValidator("^(\\d{4}-([0]\\d|[1][0-2])-([0-2]\\d|[3][0-1])$"));
    }
}
