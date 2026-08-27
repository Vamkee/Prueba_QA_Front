package com.qcautomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RemoveProductFromCart implements Task {
    private static final Target REMOVE_BUTTON = Target.the("botón para eliminar el producto del carrito")
            .locatedBy("//div[@data-test='inventory-item'][.//div[@data-test='inventory-item-name' and normalize-space()='{0}']]//button[contains(@data-test, 'remove')]");

    private final String productName;

    public RemoveProductFromCart(String productName) {
        this.productName = productName;
    }

    public static RemoveProductFromCart named(String productName) {
        return instrumented(RemoveProductFromCart.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(REMOVE_BUTTON.of(productName), isVisible()).forNoMoreThan(20).seconds(),
                JavaScriptClick.on(REMOVE_BUTTON.of(productName)),
                WaitUntil.the(REMOVE_BUTTON.of(productName), isNotVisible()).forNoMoreThan(20).seconds()
        );
    }
}
