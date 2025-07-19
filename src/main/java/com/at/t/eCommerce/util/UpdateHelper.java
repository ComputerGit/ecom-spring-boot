package com.at.t.eCommerce.util;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class UpdateHelper {

    public <S, T> void updateNonNullFields(S source, T target) {
        for (Field sourceField : source.getClass().getDeclaredFields()) {
            sourceField.setAccessible(true);
            Object value = ReflectionUtils.getField(sourceField, source);

            // Proceed only if the field has a non-null value
            if (value != null) {
                try {
                    // Find the target field with the same name
                    Field targetField = target.getClass().getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true);

                    // Check if the field types are compatible
                    if (targetField.getType().isAssignableFrom(sourceField.getType())) {
                        ReflectionUtils.setField(targetField, target, value);
                    }

                } catch (NoSuchFieldException e) {
                    // Ignore if the target doesn't have this field
                }
            }
        }
    }
}
