package util;

import constants.FormFieldsEnum;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

public class FormatUtil {

    private static NumberFormat numberFormat = new DecimalFormat("###");
    private static NumberFormat decimalFormat = new DecimalFormat("###.###");

    private static String format(final BigDecimal value, final FormFieldsEnum format) {

        if (value == null || format == null) return "";

        return format.getFormat().format(value.setScale(format.getScale(), format.getRoundingMode()));
    }

    public static String formatAll(final Map<FormFieldsEnum, BigDecimal> values, final char separator) {
        final StringBuilder sb = new StringBuilder();
        values.forEach((k, v) -> sb
                .append(k.getName())
                .append(separator)
                .append(format(v, k))
                .append(System.lineSeparator()));
        return sb.toString();
    }


    public static BigDecimal toBigDecimal(String value){
        return new BigDecimal(value);
    }

    public static Integer toInteger(String value) {
        return Integer.valueOf(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toValue(String value, Class<?> aClass) throws NumberFormatException{
        if (String.class.equals(aClass)) return (T) value;
        if (BigDecimal.class.equals(aClass)) return (T) toBigDecimal(value);
        if (Integer.class.equals(aClass)) return (T) toInteger(value);
        return (T) value;
    }
}
