package co.com.apirest.project.stepdefinition;

import co.com.apirest.project.questions.PostQuestion;
import co.com.apirest.project.taks.post.Call;
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
import static org.apache.http.HttpStatus.SC_CREATED;

public class PostStepdefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("configura la solicitud a consumir")
    public void configureRequestToConsume() {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                Call.service().
                        apiPost(
                                BASE_URL.replace(TYPE_ENVIRONMENT, ENV_QA),
                                "{\n" +
                                        "    \"name\": \"morpheus\",\n" +
                                        "    \"job\": \"leader\"\n" +
                                        "}",
                                Uri.LIST_USERS.getUri(),
                                String.valueOf(JSON))
        );
    }
    @Then("valida los datos consultados")
    public void validateStatusOfTheRequest() {
        theActorInTheSpotlight().should(seeThat(PostQuestion.registerSuccess(SC_CREATED)));
    }
}