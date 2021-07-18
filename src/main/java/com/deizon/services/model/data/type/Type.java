package com.deizon.services.model.data.type;

import com.deizon.services.model.data.validator.Validator;
import java.util.ArrayList;
import java.util.List;

public abstract class Type {

    protected static final List<Validator> validators = new ArrayList<>();

    public static Boolean isValidValue(String value) {
        for (Validator item : validators) {
            if (!item.validate(value)) {
                return false;
            }
        }

        return true;
    }

    public static List<Validator> getValidators() {
        return validators;
    }
}
