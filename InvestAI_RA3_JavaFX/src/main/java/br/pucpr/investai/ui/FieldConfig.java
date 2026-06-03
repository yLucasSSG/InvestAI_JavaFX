package br.pucpr.investai.ui;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class FieldConfig<T> {

    private final String rotulo;
    private final Function<T, String> getter;
    private final BiConsumer<T, String> setter;
    private final String placeholder;

    public FieldConfig(
            String rotulo,
            Function<T, String> getter,
            BiConsumer<T, String> setter,
            String placeholder
    ) {
        this.rotulo = rotulo;
        this.getter = getter;
        this.setter = setter;
        this.placeholder = placeholder;
    }

    public String getRotulo() {
        return rotulo;
    }

    public Function<T, String> getGetter() {
        return getter;
    }

    public BiConsumer<T, String> getSetter() {
        return setter;
    }

    public String getPlaceholder() {
        return placeholder;
    }
}