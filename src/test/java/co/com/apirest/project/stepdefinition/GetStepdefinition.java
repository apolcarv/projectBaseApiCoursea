package co.com.apirest.project.stepdefinition;

import co.com.apirest.project.questions.GetQuestion;
import co.com.apirest.project.taks.get.Call;
import co.com.apirest.project.utils.Uri;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.apirest.project.utils.Constants.*;
import static io.restassured.parsing.Parser.JSON;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.apache.http.HttpStatus.SC_OK;

public class GetStepdefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("configura la peticion a consumir")
    public void configureRequestToConsume() {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                Call.service().apiget(
                        BASE_URL.replace(TYPE_ENVIRONMENT,ENV_QA),
                        Uri.LIST_USERS.getUri(),
                        String.valueOf(JSON)));
    }

    @Then("valida estado de la peticion")
    public void validateStatusOfTheRequest() {
        theActorInTheSpotlight().should(seeThat(GetQuestion.successful(SC_OK)));
    }
}