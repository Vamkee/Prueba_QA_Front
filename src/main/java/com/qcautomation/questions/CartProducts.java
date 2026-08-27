package com.qcautomation.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

public final class CartProducts {
    private static final Target PRODUCT_NAMES = Target.the("productos del carrito")
            .locatedBy("[data-test='inventory-item-name']");
    private static final Target COMPLETE_MESSAGE = Target.the("mensaje de compra completada")
            .locatedBy("[data-test='complete-header']");
    private static final Target CHECKOUT_ERROR = Target.the("mensaje de error del checkout")
            .locatedBy("[data-test='error']");

    private CartProducts() {
    }

    public static Question<List<String>> names() {
        return actor -> PRODUCT_NAMES.resolveAllFor(actor).stream()
                .map(element -> element.getText())
                .toList();
    }

    public static Question<String> completionMessage() {
        return actor -> COMPLETE_MESSAGE.resolveFor(actor).getText();
    }

    public static Question<String> checkoutError() {
        return actor -> CHECKOUT_ERROR.resolveFor(actor).getText();
    }
}
