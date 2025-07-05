package StrategyPattern;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ResponseValidatorContext {
    private ValidationStrategy strategy;

    public void validateResponse(Response response) {
        strategy.validate(response);
    }
}
