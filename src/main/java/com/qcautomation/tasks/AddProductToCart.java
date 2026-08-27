package com.qcautomation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart implements Task {
    private static final Target PRODUCT_ADD_BUTTON = Target.the("botón para agregar el producto al carrito")
            .locatedBy("//div[@data-test='inventory-item'][.//div[@data-test='inventory-item-name' and normalize-space()='{0}']]//button[contains(@data-test, 'add-to-cart')]");
    private static final Target CART_BADGE = Target.the("contador del carrito")
            .locatedBy("[data-test='shopping-cart-badge']");

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart named(String productName) {
        return instrumented(AddProductToCart.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PRODUCT_ADD_BUTTON.of(productName), isVisible()).forNoMoreThan(20).seconds(),
                JavaScriptClick.on(PRODUCT_ADD_BUTTON.of(productName)),
                WaitUntil.the(PRODUCT_ADD_BUTTON.of(productName), isNotVisible()).forNoMoreThan(20).seconds(),
                WaitUntil.the(CART_BADGE, isVisible()).forNoMoreThan(20).seconds()
        );
    }
}
