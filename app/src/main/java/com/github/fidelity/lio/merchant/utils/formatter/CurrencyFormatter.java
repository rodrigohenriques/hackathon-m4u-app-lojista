package com.github.fidelity.lio.merchant.utils.formatter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.inject.Inject;

public class CurrencyFormatter implements Formatter<String, String> {
    @Inject
    public CurrencyFormatter() {
    }

    @Override
    public String format(String input) {
        double parsed = Double.parseDouble(input);
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format((parsed / 100)).replace("R$", "R$ ");
    }
}