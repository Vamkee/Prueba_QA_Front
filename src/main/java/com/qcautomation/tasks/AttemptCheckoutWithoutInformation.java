package com.qcautomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AttemptCheckoutWithoutInformation implements Task {
    private static final Target CHECKOUT = Target.the("botón de finalizar compra")
            .locatedBy("[data-test='checkout']");
    private static final Target CART_ITEM = Target.the("producto del carrito")
            .locatedBy("[data-test='inventory-item']");
    private static final Target CONTINUE = Target.the("botón continuar")
            .locatedBy("[data-test='continue']");
    private static final Target CHECKOUT_ERROR = Target.the("mensaje de error del checkout")
            .locatedBy("[data-test='error']");

    public static AttemptCheckoutWithoutInformation now() {
        return instrumented(AttemptCheckoutWithoutInformation.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CART_ITEM, isVisible()).forNoMoreThan(20).seconds(),
                JavaScriptClick.on(CHECKOUT),
                WaitUntil.the(CONTINUE, isVisible()).forNoMoreThan(20).seconds(),
                JavaScriptClick.on(CONTINUE),
                WaitUntil.the(CHECKOUT_ERROR, isVisible()).forNoMoreThan(20).seconds()
        );
    }
}
