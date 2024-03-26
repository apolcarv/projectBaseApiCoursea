package co.com.apirest.project.stepdefinition;

import co.com.apirest.project.questions.PutQuestion;
import co.com.apirest.project.taks.put.Call;
import co.com.apirest.project.utils.Uri;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.apirest.project.utils.Constants.*;
import static co.com.apirest.project.utils.Constants.ENV_QA;
import static io.restassured.parsing.Parser.JSON;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.apache.http.HttpStatus.SC_OK;

public class PutStepdefinition {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("configura la peticion a disipar")
    public void configureRequestADissipate() {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                Call.service()
                        .apiPut(
                                BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA),
                                "{\n" +
                                        "    \"name\": \"morpheus\",\n" +
                                        "    \"job\": \"zion resident\"\n" +
                                        "}",
                                Uri.UPDATE_USER.getUri(),
                                String.valueOf(ContentType.JSON)
                        )
        );
    }

    @Then("valida los datos consultados fueron actualizados")
    public void validatesTheDataConsultedWasUpdated() {
        theActorInTheSpotlight().should(seeThat(PutQuestion.updateSuccess(SC_OK)));
    }
}