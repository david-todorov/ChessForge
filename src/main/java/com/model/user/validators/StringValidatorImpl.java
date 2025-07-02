package com.model.user.validators;

import static com.model.user.validators.StringValidatorImpl.ValidationResult.CORRECT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.model.user.validators.StringValidatorImpl.ValidationResult;

public final class StringValidatorImpl implements FunctionConcatenator<String, ValidationResult> {

    private final List<Function<String, ValidationResult>> rules = new ArrayList<>();

    @Override
    public FunctionConcatenator<String, ValidationResult> add(final Function<String, ValidationResult> function) {
        this.rules.add(function);
        return this;
    }

    @Override
    public Function<String, ValidationResult> create() {
        return s -> this.rules.stream().map(x -> x.apply(s)).dropWhile(CORRECT::equals).findFirst().orElse(CORRECT);
    }

    public enum ValidationResult {


        CORRECT("Correct"),


        EMPTY("Empty"),


        TOO_SHORT("Too short"),


        TOO_LONG("Too long"),

        FORBIDDEN("Forbidden");

        private String message;

        ValidationResult(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

}
