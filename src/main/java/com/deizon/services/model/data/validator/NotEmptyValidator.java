package com.deizon.services.model.data.validator;

public class NotEmptyValidator implements Validator {
    @Override
    public Boolean validate(String value) {
        if (value == null) {
            return false;
        }

        return !value.isBlank();
    }
}
