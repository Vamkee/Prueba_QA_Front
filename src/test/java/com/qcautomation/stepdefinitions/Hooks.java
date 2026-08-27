package com.qcautomation.stepdefinitions;

import com.qcautomation.configurations.AppConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.JavascriptExecutor;

public class Hooks {
    @Before
    public void prepararElEscenario() {
        OnStage.setTheStage(new OnlineCast());
        Actor actor = OnStage.theActorCalled("usuario web");
        BrowseTheWeb.as(actor).getDriver().manage().deleteAllCookies();
        actor.attemptsTo(Open.url(AppConfiguration.baseUrl()));
        ((JavascriptExecutor) BrowseTheWeb.as(actor).getDriver()).executeScript(
                "window.localStorage.clear(); window.sessionStorage.clear();"
        );
    }

    @After
    public void cerrarElEscenario() {
        OnStage.drawTheCurtain();
    }
}
