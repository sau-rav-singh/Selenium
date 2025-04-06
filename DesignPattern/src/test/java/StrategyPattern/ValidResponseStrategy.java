package StrategyPattern;

import io.restassured.response.Response;

public class ValidResponseStrategy implements ValidationStrategy {
    @Override
    public void validate(Response response) {
        int expectedStatusCode = 200;
        if (response.getStatusCode() != expectedStatusCode) {
            throw new AssertionError(
                    "Expected status code " + expectedStatusCode + ", but got " + response.getStatusCode()
            );
        }
    }
}

