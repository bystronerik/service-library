package com.deizon.services.model.data.validator;

import java.util.regex.Pattern;

public class RegexValidator implements Validator {

    private final Pattern pattern;

    public RegexValidator(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Boolean validate(String value) {
        if (value == null) {
            return true;
        }

        if (value.isBlank()) {
            return true;
        }

        return this.pattern.matcher(value).matches();
    }
}
