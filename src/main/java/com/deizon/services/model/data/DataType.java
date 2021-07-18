package com.deizon.services.model.data;

import com.deizon.services.model.data.type.LongTextType;
import com.deizon.services.model.data.type.TextType;
import com.deizon.services.model.data.type.Type;

public enum DataType {
    TEXT(TextType.class),
    LONG_TEXT(LongTextType.class);

    private final Class<? extends Type> type;

    DataType(Class<? extends Type> type) {
        this.type = type;
    }

    public Class<? extends Type> get() {
        return type;
    }
}
