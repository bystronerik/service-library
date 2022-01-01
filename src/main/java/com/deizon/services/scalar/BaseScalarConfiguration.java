package com.deizon.services.scalar;

import graphql.schema.GraphQLScalarType;

public abstract class BaseScalarConfiguration {

    protected GraphQLScalarType createUploadScalar() {
        return GraphQLScalarType.newScalar()
                .name("FileUpload")
                .description("Custom FileUpload class as scalar.")
                .coercing(new FileUploadScalarCoercing())
                .build();
    }

    protected GraphQLScalarType createDateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("Java Instant as scalar. DateTime value field.")
                .coercing(new DateTimeScalarCoercing())
                .build();
    }

    protected GraphQLScalarType createFileTypeScalar() {
        return GraphQLScalarType.newScalar()
                .name("FileType")
                .description("FileType enum as scalar. File type value field.")
                .coercing(new FileTypeScalarCoercing())
                .build();
    }

    protected GraphQLScalarType createDataTypeScalar() {
        return GraphQLScalarType.newScalar()
                .name("DataType")
                .description("DataType enum as scalar. Data type value field.")
                .coercing(new DataTypeScalarCoercing())
                .build();
    }

    protected GraphQLScalarType createBigDecimalScalar() {
        return GraphQLScalarType.newScalar()
                .name("BigDecimal")
                .description("Java BigDecimal as scalar. Long decimal value field.")
                .coercing(new BigDecimalScalarCoercing())
                .build();
    }

    protected GraphQLScalarType createCurrencyScalar() {
        return GraphQLScalarType.newScalar()
                .name("Currency")
                .description("Currency enum scalar. Currency code value field.")
                .coercing(new CurrencyScalarCoercing())
                .build();
    }
}
