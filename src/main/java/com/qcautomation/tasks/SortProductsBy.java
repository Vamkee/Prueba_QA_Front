package com.qcautomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SortProductsBy implements Task {
    private static final Target SORT_SELECTOR = Target.the("selector de ordenamiento de productos")
            .locatedBy("[data-test='product-sort-container']");

    private final String option;

    public SortProductsBy(String option) {
        this.option = option;
    }

    public static SortProductsBy option(String option) {
        return instrumented(SortProductsBy.class, option);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SelectFromOptions.byVisibleText(option).from(SORT_SELECTOR));
    }
}
