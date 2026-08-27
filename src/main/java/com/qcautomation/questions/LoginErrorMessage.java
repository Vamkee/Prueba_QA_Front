package com.qcautomation.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public final class LoginErrorMessage {
    private static final Target ERROR_MESSAGE = Target.the("login error message")
            .locatedBy("[data-test='error']");

    private LoginErrorMessage() {
    }

    public static Question<String> displayed() {
        return actor -> ERROR_MESSAGE.resolveFor(actor).getText();
    }
}
