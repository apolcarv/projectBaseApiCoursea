package co.com.apirest.project.interactions;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.thucydides.core.util.EnvironmentVariables;

@Slf4j
public class DeleteRequest implements Interaction {

    private EnvironmentVariables environmentVariables;
    private static final String MESSAGE_GENERAL = "El servicio se consumio de forma exitosa";
    private String baseUrl;
    private final String request;
    private final String typeContent;

    public DeleteRequest(String baseUrl, String request, String typeContent) {
        this.baseUrl = baseUrl;
        this.request = request;
        this.typeContent = typeContent;
    }

    @Override
    @Subject("{0} #nameActor obtiene urlBase: #baseUrl - segun su ambiente: #typeContent, y consumen el servicio")
    public <T extends Actor> void performAs(T actor) {
        String pathBaseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(baseUrl);
        actor.whoCan(CallAnApi.at(pathBaseUrl));
        actor.attemptsTo(
                Delete
                        .from(request)
                        .with(requestSpecification -> requestSpecification
                                .accept(typeContent)
                                .relaxedHTTPSValidation())
        );

        log.info(MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static DeleteRequest params(String baseUrl, String request, String typeContent) {
        return Tasks.instrumented(DeleteRequest.class, baseUrl, request, typeContent);
    }
}