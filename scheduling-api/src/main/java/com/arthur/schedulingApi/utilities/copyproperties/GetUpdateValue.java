package com.arthur.schedulingApi.utilities.copyproperties;

import java.util.List;

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

    public static <E> List<E>  getUpdatedListValue(List<E> newListValue, List<E> oldListValue) {

        if (newListValue == null) {
            return oldListValue;
        }

        return newListValue;
    }

}
