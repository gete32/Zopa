package exception;

public class DuplicateCsvRecordException extends Exception {

    public DuplicateCsvRecordException() {
        super("Duplication CSV record. We have to have primary key.");
    }
}
