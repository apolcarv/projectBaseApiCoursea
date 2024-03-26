package co.com.apirest.project.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static co.com.apirest.project.utils.Constants.RESPONSE_REQUEST;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class PostQuestion implements Question<Boolean> {

    private int statusOK;

    public PostQuestion(int statusOK) {
        this.statusOK = statusOK;
    }


    @Override
    @Subject("{0} valida el codigo de estado OK #statusDelete ")
    public Boolean answeredBy(Actor actor) {
        actor.should(seeThatResponse(RESPONSE_REQUEST,
                reponse -> reponse.statusCode(statusOK)
                        .body("name", equalTo("morpheus"))
                        .body("job", equalTo("leader"))
        )
    );
        return true;
    }

    public static PostQuestion registerSuccess(int statusOK) {
        return new PostQuestion(statusOK);
    }
}