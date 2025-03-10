package com.example.cakeprj.util;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PriceFormatter {
    public static String formatPrice(double price) {
        double adjustedPrice = price * 1000;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);

        return formatter.format(adjustedPrice);
    }
}