package co.com.apirest.project.taks.get;

import co.com.apirest.project.interactions.GetRequest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class ConsumeServiceGet {

    public Performable apiget(String baseUrl, String request, String typeContent) {
        return Task.where("{0} call the service at execute GET",
                GetRequest.params(baseUrl, request, typeContent)
        );
    }
}
