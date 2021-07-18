package com.deizon.services.scalar;

import com.deizon.services.model.data.DataType;

public class DataTypeScalarCoercing extends EnumScalarCoercing<DataType> {

    public DataTypeScalarCoercing() {
        super(DataType.class);
    }
}
