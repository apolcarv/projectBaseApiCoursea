package co.com.apirest.project.taks.delete;

import co.com.apirest.project.interactions.GetRequest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class ConsumeServiceDelete {

    public Performable apiDelete(String baseUrl, String request, String typeContent) {
        return Task.where("{0} call the service at execute DELETE",
                GetRequest.params(baseUrl, request, typeContent)
        );
    }
}