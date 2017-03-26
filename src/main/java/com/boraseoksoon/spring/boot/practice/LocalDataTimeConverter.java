package com.boraseoksoon.spring.boot.practice;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by seoksoonjang on 2017. 3. 26..
 */
@Converter(autoApply = true)
public class LocalDataTimeConverter  implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
