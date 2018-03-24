package exception;

public class DuplicateCsvFieldException extends Exception {

    private static final String MSG = "Duplicate CSV field";

    public DuplicateCsvFieldException() {
        super(MSG);
    }

    public DuplicateCsvFieldException(String message) {
        super(MSG + " :" + message);
    }
}
