package com.model.user.validators;

import java.util.function.Function;

public interface FunctionConcatenator<T, R> {

    FunctionConcatenator<T, R> add(Function<T, R> function);

    Function<T, R> create();
}
