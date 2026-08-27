package com.qcautomation.stepdefinitions;

import com.qcautomation.configurations.AppConfiguration;
import com.qcautomation.models.Credentials;
import com.qcautomation.models.CustomerInformation;
import com.qcautomation.questions.CartItemCount;
import com.qcautomation.questions.CartProducts;
import com.qcautomation.questions.DisplayedProducts;
import com.qcautomation.questions.FirstDisplayedProduct;
import com.qcautomation.questions.LoginErrorMessage;
import com.qcautomation.questions.ProductsPageTitle;
import com.qcautomation.tasks.AddProductToCart;
import com.qcautomation.tasks.CompletePurchase;
import com.qcautomation.tasks.Login;
import com.qcautomation.tasks.OpenCart;
import com.qcautomation.tasks.RemoveProductFromCart;
import com.qcautomation.tasks.AttemptCheckoutWithoutInformation;
import com.qcautomation.tasks.SortProductsBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class AuthenticationStepDefinitions {
    private static final int ASSERTION_TIMEOUT_SECONDS = 20;
    private static final Target PAGE_TITLE = Target.the("título de la página")
            .locatedBy("[data-test='title']");
    private static final Target PRODUCT_NAMES = Target.the("nombres de productos")
            .locatedBy("[data-test='inventory-item-name']");
    private static final Target CART_BADGE = Target.the("contador del carrito")
            .locatedBy("[data-test='shopping-cart-badge']");
    private static final Target FIRST_PRODUCT_NAME = Target.the("nombre del primer producto")
            .locatedBy("[data-test='inventory-item']:first-child [data-test='inventory-item-name']");
    private static final Target FIRST_PRODUCT_PRICE = Target.the("precio del primer producto")
            .locatedBy("[data-test='inventory-item']:first-child [data-test='inventory-item-price']");
    private static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .locatedBy("[data-test='error']");
    private static final Target COMPLETION_MESSAGE = Target.the("mensaje de compra completada")
            .locatedBy("[data-test='complete-header']");

    private Actor actor() {
        return OnStage.theActorInTheSpotlight();
    }

    private void waitFor(Target target) {
        actor().attemptsTo(
                WaitUntil.the(target, isVisible()).forNoMoreThan(ASSERTION_TIMEOUT_SECONDS).seconds()
        );
    }

    @Given("que el usuario está en la página de inicio de sesión de SauceDemo")
    public void theUserIsOnTheSauceDemoLoginPage() {
        actor().attemptsTo(Open.url(AppConfiguration.baseUrl()));
    }

    @When("el usuario inicia sesión con usuario {string} y contraseña {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        actor().attemptsTo(Login.with(new Credentials(username, password)));
    }

    @Then("se muestra la página de productos")
    public void theProductsPageIsDisplayed() {
        waitFor(PAGE_TITLE);
        actor().should(seeThat(ProductsPageTitle.displayed(), equalTo("Products")));
    }

    @Then("se muestra el mensaje de error de inicio de sesión {string}")
    public void theLoginErrorMessageIsDisplayed(String expectedMessage) {
        waitFor(ERROR_MESSAGE);
        actor().should(seeThat(LoginErrorMessage.displayed(), equalTo(expectedMessage)));
    }

    @Given("que el usuario ha iniciado sesión con un usuario válido")
    public void elUsuarioHaIniciadoSesionConUnUsuarioValido() {
        actor().attemptsTo(
                Open.url(AppConfiguration.baseUrl()),
                Login.with(new Credentials("standard_user", "secret_sauce"))
        );
    }

    @Then("se muestran {int} productos en el catálogo")
    public void seMuestranProductosEnElCatalogo(int expectedProductCount) {
        waitFor(PRODUCT_NAMES);
        actor().should(seeThat(DisplayedProducts.names(), hasSize(expectedProductCount)));
    }

    @Then("el catálogo contiene el producto {string}")
    public void elCatalogoContieneElProducto(String productName) {
        waitFor(PRODUCT_NAMES);
        actor().should(seeThat(DisplayedProducts.names(), hasItem(productName)));
    }

    @When("el usuario agrega el producto {string} al carrito")
    public void elUsuarioAgregaElProductoAlCarrito(String productName) {
        actor().attemptsTo(AddProductToCart.named(productName));
    }

    @Then("el contador del carrito muestra {string}")
    public void elContadorDelCarritoMuestra(String expectedCount) {
        waitFor(CART_BADGE);
        actor().should(seeThat(CartItemCount.displayed(), equalTo(expectedCount)));
    }

    @When("el usuario ordena los productos por {string}")
    public void elUsuarioOrdenaLosProductosPor(String option) {
        actor().attemptsTo(SortProductsBy.option(option));
    }

    @Then("el primer producto mostrado es {string}")
    public void elPrimerProductoMostradoEs(String expectedProductName) {
        waitFor(FIRST_PRODUCT_NAME);
        actor().should(seeThat(FirstDisplayedProduct.name(), equalTo(expectedProductName)));
    }

    @Then("el precio del primer producto mostrado es {string}")
    public void elPrecioDelPrimerProductoMostradoEs(String expectedPrice) {
        waitFor(FIRST_PRODUCT_PRICE);
        actor().should(seeThat(FirstDisplayedProduct.price(), equalTo(expectedPrice)));
    }

    @When("el usuario abre el carrito")
    public void elUsuarioAbreElCarrito() {
        actor().attemptsTo(OpenCart.now());
    }

    @When("el usuario elimina del carrito el producto {string}")
    public void elUsuarioEliminaDelCarritoElProducto(String productName) {
        actor().attemptsTo(RemoveProductFromCart.named(productName));
    }

    @Then("el carrito contiene los productos {string} y {string}")
    public void elCarritoContieneLosProductos(String firstProduct, String secondProduct) {
        waitFor(PRODUCT_NAMES);
        actor().should(
                seeThat(CartProducts.names(), hasItem(firstProduct)),
                seeThat(CartProducts.names(), hasItem(secondProduct))
        );
    }

    @Then("el carrito contiene únicamente el producto {string}")
    public void elCarritoContieneUnicamenteElProducto(String productName) {
        waitFor(PRODUCT_NAMES);
        actor().should(seeThat(CartProducts.names(), equalTo(java.util.List.of(productName))));
    }

    @When("el usuario diligencia los datos de compra con nombre {string}, apellido {string} y código postal {string}")
    public void elUsuarioDiligenciaLosDatosDeCompra(String firstName, String lastName, String postalCode) {
        actor().attemptsTo(
                CompletePurchase.with(new CustomerInformation(firstName, lastName, postalCode))
        );
    }

    @Then("se muestra el mensaje de compra completada {string}")
    public void seMuestraElMensajeDeCompraCompletada(String expectedMessage) {
        waitFor(COMPLETION_MESSAGE);
        actor().should(seeThat(CartProducts.completionMessage(), equalTo(expectedMessage)));
    }

    @Then("el carrito no contiene productos")
    public void elCarritoNoContieneProductos() {
        waitFor(PAGE_TITLE);
        actor().should(seeThat(CartProducts.names(), hasSize(0)));
    }

    @When("el usuario intenta continuar el checkout sin diligenciar datos")
    public void elUsuarioIntentaContinuarElCheckoutSinDiligenciarDatos() {
        actor().attemptsTo(AttemptCheckoutWithoutInformation.now());
    }

    @Then("se muestra el error de checkout {string}")
    public void seMuestraElErrorDeCheckout(String expectedError) {
        waitFor(ERROR_MESSAGE);
        actor().should(seeThat(CartProducts.checkoutError(), equalTo(expectedError)));
    }
}
