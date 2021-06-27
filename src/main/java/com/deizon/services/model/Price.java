package com.deizon.services.model;

import java.math.BigDecimal;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Price {

    private BigDecimal value;
    private Currency currency;

    public enum Currency {
        USD,
        CZK,
        PLN,
        EUR,
        GBP,
        AUD,
        RUB
    }
}
