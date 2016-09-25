package com.github.fidelity.lio.merchant.di.module;

import com.github.fidelity.lio.merchant.utils.formatter.CurrencyFormatter;
import com.github.fidelity.lio.merchant.utils.formatter.Formatter;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {
    @Provides
    Formatter<String, String> provideCurrencyFormatter(CurrencyFormatter currencyFormatter) {
        return currencyFormatter;
    }
}
