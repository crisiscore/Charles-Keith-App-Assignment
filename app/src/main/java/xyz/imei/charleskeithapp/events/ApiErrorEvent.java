package xyz.imei.charleskeithapp.events;

public class ApiErrorEvent {
    private String message;

    public ApiErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
