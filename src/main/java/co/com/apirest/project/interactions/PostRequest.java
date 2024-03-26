package co.com.apirest.project.interactions;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.util.EnvironmentVariables;


@Slf4j
public class PostRequest implements Interaction {

    private EnvironmentVariables environmentVariables;
    private static final String MESSAGE_GENERAL = "El servicio se consumio de forma exitosa";
    private String baseUrl;
    private String bodyRequest;
    private final String requestUri;
    private final String typeContent;

    public PostRequest(String baseUrl, String bodyRequest, String requestUri, String typeContent) {
        this.baseUrl = baseUrl;
        this.bodyRequest = bodyRequest;
        this.requestUri = requestUri;
        this.typeContent = typeContent;
    }

    @Override
    @Subject("{0} #nameActor obtiene urlBase: #baseUrl - segun su ambiente: #typeContent, y consumen el servicio")
    public <T extends Actor> void performAs(T actor) {
        String pathBaseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty(baseUrl);
        actor.whoCan(CallAnApi.at(pathBaseUrl));
        actor.attemptsTo(
               Post.to(requestUri)
                       .with(request -> request
                               .contentType(typeContent)
                               .body(bodyRequest)
                               .relaxedHTTPSValidation())
        );
        log.info(MESSAGE_GENERAL);
        SerenityRest.lastResponse().body().prettyPrint();
    }

    public static PostRequest params(String baseUrl, String bodyRequest, String requestUri, String typeContent) {
        return Tasks.instrumented(PostRequest.class, baseUrl, bodyRequest, requestUri, typeContent);
    }
}
