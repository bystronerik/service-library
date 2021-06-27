package com.deizon.services.scalar;

import java.math.BigDecimal;

public class BigDecimalScalarCoercing extends StringInputScalarCoercing<BigDecimal> {

    public BigDecimalScalarCoercing() {
        super(BigDecimal.class);
    }

    @Override
    protected String serializeToOutput(BigDecimal data) {
        return data.toString();
    }

    @Override
    protected BigDecimal parseFromInput(String input) {
        return new BigDecimal(input);
    }
}
