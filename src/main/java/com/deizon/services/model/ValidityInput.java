package com.deizon.services.model;

import java.time.Instant;

public interface ValidityInput {

    Boolean getValidityEnabled();

    Instant getValidFrom();

    Instant getValidTo();

    default Validity getValidity() {
        if (this.getValidityEnabled() == null
                || this.getValidTo() == null
                || this.getValidFrom() == null) return null;
        return new Validity(this.getValidityEnabled(), this.getValidFrom(), this.getValidTo());
    }
}
