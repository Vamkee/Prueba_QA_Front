package com.qcautomation.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

public final class DisplayedProducts {
    private static final Target PRODUCT_NAMES = Target.the("nombres de los productos")
            .locatedBy("[data-test='inventory-item-name']");

    private DisplayedProducts() {
    }

    public static Question<List<String>> names() {
        return actor -> PRODUCT_NAMES.resolveAllFor(actor).stream()
                .map(element -> element.getText())
                .toList();
    }
}
