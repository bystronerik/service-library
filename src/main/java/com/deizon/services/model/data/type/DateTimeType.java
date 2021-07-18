package com.deizon.services.model.data.type;

import com.deizon.services.model.data.validator.RegexValidator;

public class DateTimeType extends Type {

    static {
        validators.add(
                new RegexValidator(
                        "^(\\d{4}-([0]\\d|[1][0-2])-([0-2]\\d|[3][0-1])T([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)$"));
    }
}
