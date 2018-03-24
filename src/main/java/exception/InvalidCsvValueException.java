package exception;

public class InvalidCsvValueException extends Exception {

    private static final String MSG = "Invalid csv value";

    public InvalidCsvValueException() {
        super(MSG);
    }

    public InvalidCsvValueException(String message) {
        super(MSG + " " + message);
    }
}
