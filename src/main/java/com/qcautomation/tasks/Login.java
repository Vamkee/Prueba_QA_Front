package com.qcautomation.tasks;

import com.qcautomation.models.Credentials;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {
    private static final Target USERNAME = Target.the("username field")
            .locatedBy("[data-test='username']");
    private static final Target PASSWORD = Target.the("password field")
            .locatedBy("[data-test='password']");
    private static final Target LOGIN_BUTTON = Target.the("login button")
            .locatedBy("[data-test='login-button']");

    private final Credentials credentials;

    public Login(Credentials credentials) {
        this.credentials = credentials;
    }

    public static Login with(Credentials credentials) {
        return instrumented(Login.class, credentials);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(credentials.username()).into(USERNAME),
                Enter.theValue(credentials.password()).into(PASSWORD),
                Click.on(LOGIN_BUTTON)
        );
    }
}
