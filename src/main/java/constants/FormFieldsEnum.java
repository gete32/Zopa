package constants;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public enum FormFieldsEnum {

    REQUESTED_AMOUNT("Requested amount"),
    RATE("Rate"),
    MONTHLY_REPAYMENT("Monthly repayment"),
    TOTAL_REPAYMENT("Total repayment");

    private String name;
    private NumberFormat format;
    private int scale;
    private RoundingMode roundingMode;

    FormFieldsEnum(String name, NumberFormat format, int scale, RoundingMode roundingMode) {
        this.name = name;
        this.format = format;
        this.scale = scale;
        this.roundingMode = roundingMode;
    }

    FormFieldsEnum(String name) {
        this.name = name;
        this.format = NumberFormat.getCurrencyInstance(Locale.UK);
        this.scale = 2;
        this.roundingMode = RoundingMode.HALF_UP;
    }

    public String getName() {
        return name;
    }

    public NumberFormat getFormat() {
        return format;
    }

    public int getScale() {
        return scale;
    }

    public RoundingMode getRoundingMode() {
        return roundingMode;
    }
}
