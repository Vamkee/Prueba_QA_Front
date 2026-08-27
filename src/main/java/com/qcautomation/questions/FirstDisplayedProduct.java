package com.qcautomation.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public final class FirstDisplayedProduct {
    private static final Target NAME = Target.the("nombre del primer producto")
            .locatedBy("[data-test='inventory-item']:first-child [data-test='inventory-item-name']");
    private static final Target PRICE = Target.the("precio del primer producto")
            .locatedBy("[data-test='inventory-item']:first-child [data-test='inventory-item-price']");

    private FirstDisplayedProduct() {
    }

    public static Question<String> name() {
        return actor -> NAME.resolveFor(actor).getText();
    }

    public static Question<String> price() {
        return actor -> PRICE.resolveFor(actor).getText();
    }
}
