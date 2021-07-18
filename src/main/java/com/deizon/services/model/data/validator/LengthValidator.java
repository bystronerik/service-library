package com.deizon.services.model.data.validator;

public class LengthValidator implements Validator {

    private final Integer minLength;
    private final Integer maxLength;

    public LengthValidator(Integer minLength, Integer maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public Boolean validate(String value) {
        if (value == null) {
            return true;
        }

        return value.length() > this.minLength && value.length() < maxLength;
    }
}
