package com.example.cakeprj.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PriceFormatter {
    public static String formatPrice(double price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);

        return formatter.format(price);
    }
}
