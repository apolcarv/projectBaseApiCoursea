package co.com.apirest.project.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static co.com.apirest.project.utils.Constants.STATUS;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class DeleteQuestion implements Question<Boolean> {

    private int statusDelete;

    public DeleteQuestion(int statusDelete) {
        this.statusDelete = statusDelete;
    }

    @Override
    @Subject("{0} valida el codigo de estado No Content #statusDelete ")
    public Boolean answeredBy(Actor actor) {
        actor.should(seeThatResponse(STATUS, response -> response.statusCode(statusDelete)
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))));
        return true;
    }
    public static DeleteQuestion successFul(int statusDelete){
        return new DeleteQuestion(statusDelete);
    }
}