package StrategyPattern;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Main {
    public static void main(String[] args) {
        ResponseValidatorContext context = new ResponseValidatorContext(new ValidResponseStrategy());

        Response validResponse = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        context.validateResponse(validResponse);

        Response invalidResponse = RestAssured.get("https://jsonplaceholder.typicode.com/invalid");
        context.setStrategy(new InvalidResponseStrategy());
        context.validateResponse(invalidResponse);

        System.out.println("Test Passed");
    }
}
