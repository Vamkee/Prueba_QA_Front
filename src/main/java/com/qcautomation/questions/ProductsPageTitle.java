package com.qcautomation.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public final class ProductsPageTitle {
    private static final Target TITLE = Target.the("products page title")
            .locatedBy("[data-test='title']");

    private ProductsPageTitle() {
    }

    public static Question<String> displayed() {
        return actor -> TITLE.resolveFor(actor).getText();
    }
}
