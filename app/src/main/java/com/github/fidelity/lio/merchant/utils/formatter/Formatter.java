package com.github.fidelity.lio.merchant.utils.formatter;

public interface Formatter<Input, Result> {
    Result format(Input input);
}