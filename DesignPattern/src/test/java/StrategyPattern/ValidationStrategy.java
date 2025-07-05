package StrategyPattern;

import io.restassured.response.Response;

@FunctionalInterface
public interface ValidationStrategy {
    void validate(Response response);
}
