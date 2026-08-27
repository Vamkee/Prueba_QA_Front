package com.qcautomation.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public final class CartItemCount {
    private static final Target BADGE = Target.the("contador de productos del carrito")
            .locatedBy("[data-test='shopping-cart-badge']");

    private CartItemCount() {
    }

    public static Question<String> displayed() {
        return actor -> BADGE.resolveFor(actor).getText();
    }
}
