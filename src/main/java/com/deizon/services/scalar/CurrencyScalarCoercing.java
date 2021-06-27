package com.deizon.services.scalar;

import com.deizon.services.model.Price.Currency;

public class CurrencyScalarCoercing extends EnumScalarCoercing<Currency> {

    public CurrencyScalarCoercing() {
        super(Currency.class);
    }
}
