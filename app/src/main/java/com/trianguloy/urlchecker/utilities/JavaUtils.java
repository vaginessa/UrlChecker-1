package com.trianguloy.urlchecker.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Generic Java utils.
 * I prefer smaller and more available apps, even if they require an older API and not using Kotlin
 */
public interface JavaUtils {

    /**
     * Converts an iterator to a list
     */
    static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * Converts a string into a json object, returns empty on failure
     */
    static JSONObject toJson(String content) {
        try {
            return new JSONObject(content);
        } catch (JSONException e) {
            // invalid catalog, return empty
            return new JSONObject();
        }
    }

    /**
     * Clamps a value between two other values.
     */
    static int clamp(int min, int value, int max) {
        return min <= max ? Math.max(min, Math.min(value, max))
                // just in case
                : clamp(max, value, min);
    }

    /**
     * java.util.function.Consumer requires api 24
     */
    @FunctionalInterface
    interface Consumer<T> {
        void accept(T t);
    }

    /**
     * java.util.function.Function requires api 24
     */
    @FunctionalInterface
    interface Function<T, R> {
        R apply(T t);
    }

    /**
     * java.util.function.UnaryOperator requires api 24
     */
    @FunctionalInterface
    interface UnaryOperator<T> extends Function<T, T> {
    }
}
