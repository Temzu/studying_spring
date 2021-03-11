package com.temzu.spring.data.model;

import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, SortDirection> {
    @Override
    public SortDirection convert(String source) {
        try {
            return SortDirection.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return SortDirection.ASC;
        }
    }
}
