package com.qcautomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenCart implements Task {
    private static final Target CART = Target.the("enlace del carrito")
            .locatedBy("[data-test='shopping-cart-link']");
    private static final Target CART_TITLE = Target.the("título del carrito")
            .locatedBy("[data-test='title']");

    public static OpenCart now() {
        return instrumented(OpenCart.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                JavaScriptClick.on(CART),
                WaitUntil.the(CART_TITLE, isVisible()).forNoMoreThan(20).seconds()
        );
    }
}
