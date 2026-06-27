package com.stockpilot.util;

import java.util.regex.Pattern;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    private static final Pattern SKU_PATTERN =
            Pattern.compile("^[A-Z]{3}-\\d{4}$");

    public static boolean isValidSku(String sku) {
        return SKU_PATTERN.matcher(sku).matches();
    }
}