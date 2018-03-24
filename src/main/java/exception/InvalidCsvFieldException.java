package exception;

public class InvalidCsvFieldException extends Exception {

    private static final String MSG = "Invalid CSV fields. There is no necessary field";

    public InvalidCsvFieldException() {
        super(MSG);
    }

    public InvalidCsvFieldException(String message) {
        super(MSG + " " + message);
    }
}
