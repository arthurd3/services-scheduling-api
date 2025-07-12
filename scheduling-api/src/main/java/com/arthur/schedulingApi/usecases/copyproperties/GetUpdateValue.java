package com.arthur.schedulingApi.usecases.copyproperties;

public class GetUpdateValue {

    public static <T> T getUpdatedValue(T newValue, T oldValue) {

        if (newValue == null) {
            return oldValue;
        }

        if (newValue instanceof String) {
            if (((String) newValue).isBlank()) {
                return oldValue;
            }
        }

        return newValue;
    }

}
