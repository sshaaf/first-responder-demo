package org.cajun.navy.map;

public class RoutePlannerException extends RuntimeException {

    public RoutePlannerException(String message) {
        super(message);
    }

    public RoutePlannerException(String message, Exception cause) {
        super(message, cause);
    }
}
