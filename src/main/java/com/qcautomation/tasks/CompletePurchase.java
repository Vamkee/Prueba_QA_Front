package com.qcautomation.tasks;

import com.qcautomation.models.CustomerInformation;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompletePurchase implements Task {
    private static final Target CHECKOUT = Target.the("botón de finalizar compra")
            .locatedBy("[data-test='checkout']");
    private static final Target CART_ITEM = Target.the("producto del carrito")
            .locatedBy("[data-test='inventory-item']");
    private static final Target FIRST_NAME = Target.the("campo de nombre")
            .locatedBy("[data-test='firstName']");
    private static final Target LAST_NAME = Target.the("campo de apellido")
            .locatedBy("[data-test='lastName']");
    private static final Target POSTAL_CODE = Target.the("campo de código postal")
            .locatedBy("[data-test='postalCode']");
    private static final Target CONTINUE = Target.the("botón continuar")
            .locatedBy("[data-test='continue']");
    private static final Target OVERVIEW_TITLE = Target.the("título del resumen de compra")
            .locatedBy("[data-test='title']");
    private static final Target OVERVIEW_CONTAINER = Target.the("resumen de compra")
            .locatedBy("[data-test='checkout-summary-container']");
    private static final Target FINISH = Target.the("botón finalizar")
            .locatedBy("[data-test='finish']");
    private static final Target COMPLETE_MESSAGE = Target.the("mensaje de compra completada")
            .locatedBy("[data-test='complete-header']");

    private final CustomerInformation customerInformation;

    public CompletePurchase(CustomerInformation customerInformation) {
        this.customerInformation = customerInformation;
    }

    public static CompletePurchase with(CustomerInformation customerInformation) {
        return instrumented(CompletePurchase.class, customerInformation);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CART_ITEM, isVisible()).forNoMoreThan(20).seconds(),
                JavaScriptClick.on(CHECKOUT),
                WaitUntil.the(FIRST_NAME, isVisible()).forNoMoreThan(20).seconds(),
                WaitUntil.the(LAST_NAME, isVisible()).forNoMoreThan(20).seconds(),
                WaitUntil.the(POSTAL_CODE, isVisible()).forNoMoreThan(20).seconds()
        );
        setInputValue(actor, FIRST_NAME.resolveFor(actor), customerInformation.firstName());
        setInputValue(actor, LAST_NAME.resolveFor(actor), customerInformation.lastName());
        setInputValue(actor, POSTAL_CODE.resolveFor(actor), customerInformation.postalCode());
        actor.attemptsTo(
                WaitUntil.the(CONTINUE, isVisible()).forNoMoreThan(20).seconds(),
                JavaScriptClick.on(CONTINUE),
                WaitUntil.the(OVERVIEW_CONTAINER, isVisible()).forNoMoreThan(20).seconds(),
                WaitUntil.the(OVERVIEW_TITLE, isVisible()).forNoMoreThan(20).seconds(),
                WaitUntil.the(FINISH, isVisible()).forNoMoreThan(20).seconds(),
                JavaScriptClick.on(FINISH),
                WaitUntil.the(COMPLETE_MESSAGE, isVisible()).forNoMoreThan(20).seconds()
        );
    }

    private void setInputValue(Actor actor, WebElement input, String value) {
        ((JavascriptExecutor) BrowseTheWeb.as(actor).getDriver()).executeScript(
                "const input = arguments[0];" +
                        "const setter = Object.getOwnPropertyDescriptor(" +
                        "window.HTMLInputElement.prototype, 'value').set;" +
                        "setter.call(input, arguments[1]);" +
                        "input.dispatchEvent(new Event('input', {bubbles: true}));" +
                        "input.dispatchEvent(new Event('change', {bubbles: true}));" +
                        "input.dispatchEvent(new Event('blur', {bubbles: true}));",
                input,
                value
        );
    }
}
