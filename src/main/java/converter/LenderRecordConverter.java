package converter;

import constants.FileFieldsEnum;
import entity.Lender;
import exception.InvalidCsvValueException;
import org.apache.commons.csv.CSVRecord;
import util.FormatUtil;

import java.util.Map;

import static constants.FileFieldsEnum.*;

public class LenderRecordConverter implements Converter<CSVRecord, Lender, Map<FileFieldsEnum, Integer>> {

    private <T> T getValue(CSVRecord record, Integer index, Class<?> type) throws InvalidCsvValueException {
        if (record == null || index == null || type == null) return null;
        String value = record.get(index);
        try {
            return FormatUtil.toValue(value, type);
        } catch (NumberFormatException e) {
            throw new InvalidCsvValueException(value);
        }
    }

    @Override
    public Lender convert(CSVRecord source, Map<FileFieldsEnum, Integer> header) throws InvalidCsvValueException{
        Lender lender = new Lender();
        lender.setLender(getValue(source, header.get(LENDER), LENDER.getaClass()));
        lender.setAvailable(getValue(source, header.get(AVAILABLE), AVAILABLE.getaClass()));
        lender.setRate(getValue(source,header.get(RATE), RATE.getaClass()));
        return lender;
    }
}
