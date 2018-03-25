package service;

import constants.FileFieldsEnum;
import constants.SeparatorEnum;
import converter.Converter;
import converter.LenderRecordConverter;
import entity.Lender;
import exception.DuplicateCsvFieldException;
import exception.InvalidCsvFieldException;
import exception.InvalidCsvValueException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class ParseServiceImpl implements ParseService {

    private final static CSVFormat CSV_FORMAT = CSVFormat.DEFAULT
            .withDelimiter(SeparatorEnum.CSV_SEPARATOR.getSeparator())
            .withIgnoreSurroundingSpaces()
            .withHeader();

    private final Log log = LogFactory.getLog(this.getClass());

    private final Converter<CSVRecord, Lender, Map<FileFieldsEnum, Integer>> converter;

    public ParseServiceImpl() {
        converter = new LenderRecordConverter();
    }

    private Map<FileFieldsEnum, Integer> convertHeader(final Map<String, Integer> header) throws InvalidCsvFieldException, DuplicateCsvFieldException {
        if (header == null) return Collections.emptyMap();

        Map<FileFieldsEnum, Integer> fieldIndexMap = new HashMap<>();
        for (FileFieldsEnum field : FileFieldsEnum.values()) {
            String name = field.getName();
            Integer index = header.get(name);
            if (index == null) throw new InvalidCsvFieldException(name);
            if (fieldIndexMap.putIfAbsent(field, index) != null) throw new DuplicateCsvFieldException(name);
        }
        return fieldIndexMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Lender> parse(String path) {
        File file = new File(path);
        List<Lender> lenders = new ArrayList<>();
        try (CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSV_FORMAT)){
            Map<FileFieldsEnum, Integer> header = convertHeader(parser.getHeaderMap());
            for (CSVRecord record : parser)
                lenders.add(converter.convert(record, header));
        } catch (IOException | DuplicateCsvFieldException | InvalidCsvFieldException | InvalidCsvValueException e) {
            log.error(this.getClass().getName() + ": parse ERROR: " + e.getMessage());
        }
        return lenders;
    }

}
