package StrategyPattern;

import io.restassured.response.Response;

import java.util.Set;

public class InvalidResponseStrategy implements ValidationStrategy {
    private static final Set<Integer> INVALID_STATUS_CODES = Set.of(400, 404, 500);

    @Override
    public void validate(Response response) {
        if (!INVALID_STATUS_CODES.contains(response.getStatusCode())) {
            throw new AssertionError(
                    "Did not expect status code " + response.getStatusCode() + ", which is considered invalid"
            );
        }
    }
}
