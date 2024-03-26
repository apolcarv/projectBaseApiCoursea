package co.com.apirest.project.stepdefinition;

import co.com.apirest.project.questions.DeleteQuestion;
import co.com.apirest.project.taks.delete.Call;
import co.com.apirest.project.utils.Uri;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.apirest.project.utils.Constants.*;
import static co.com.apirest.project.utils.Constants.ENV_QA;
import static io.restassured.http.ContentType.JSON;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;

public class DeleteStepdefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("estructura la peticion a consumir")
    public void structureTheRequestToConsume() {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                Call.service().apiDelete(
                        BASE_URL.replace(TYPE_ENVIRONMENT, ENV_DEV),
                        Uri.DELETE.getUri(),
                        String.valueOf(JSON)
                ));
    }
    @Then("el estado de la petición válido, confirma la eliminación exitosa del usuario")
    public void ValidRequestStatusConfirmsSuccessfulDeletionOfUser() {
        theActorInTheSpotlight().should(seeThat(DeleteQuestion.successFul(SC_OK)));
    }
}