package com.deizon.services.scalar;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeScalarCoercing extends StringInputScalarCoercing<Instant> {

    public DateTimeScalarCoercing() {
        super(Instant.class);
    }

    @Override
    protected String serializeToOutput(Instant data) {
        return ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("UTC+1")).format(data);
    }

    @Override
    protected Instant parseFromInput(String input) {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME
                .withZone(ZoneId.of("Europe/Prague"))
                .parse(input, Instant::from);
    }
}
