package co.com.apirest.project.taks.put;

import co.com.apirest.project.interactions.PutRequest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class ConsumeServicePut {

    public Performable apiPut(String baseUrl, String bodyRequest, String request, String typeContent) {
        return Task.where("{0} call the service at execute PUT",
                PutRequest.params(baseUrl, bodyRequest, request, typeContent));
    }
}