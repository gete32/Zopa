package constants;

public enum SeparatorEnum {

    CSV_SEPARATOR(','),
    OUTPUT_SEPARATOR(':');

    private char separator;

    SeparatorEnum(char separator) {
        this.separator = separator;
    }

    public char getSeparator() {
        return separator;
    }
}
