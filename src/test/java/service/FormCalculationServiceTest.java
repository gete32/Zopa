package service;

import constants.FormFieldsEnum;
import entity.Summable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static constants.FormFieldsEnum.*;
import static constants.SeparatorEnum.OUTPUT_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.FormatUtil.*;

public class FormCalculationServiceTest extends AbstractServiceTest {

    private static int MONTHS = 36;

    private static CalculationService<Summable> strategy;
    private static ParseService parseService;
    private static FormCalculationService formCalculationService;

    @BeforeAll
    static void init() {
        strategy = new CalculationServiceImpl();
        parseService = new ParseServiceImpl();
        formCalculationService = new FormCalculationServiceImpl();
    }

    @ParameterizedTest
    @CsvSource({
            "testCSV.csv, 1000, 7, 30.86, 1110.84",
            "testCSV.csv, 660, 7.16, 20.39, 734.14",
            "testCSV.csv, 200, 7.31, 6.24, 224.67",
            "testCSV.csv, 2330, 7.56, 72.35, 2604.60"
    })
    void test(String file, int request, double rate, double monthRepayment, double total) {
        List<Summable> lenders = parseService.parse(getUrl(file).getFile())
                .stream()
                .sorted(Comparator.reverseOrder())
                .map(e -> (Summable) e)
                .collect(Collectors.toList());
        final List<? extends Summable> result = strategy.calculate(lenders, request);
        final Map<FormFieldsEnum, BigDecimal> form = formCalculationService.calculate(convert(result), request, MONTHS);
        assertTrue(equalsDecim(form.get(TOTAL_REPAYMENT), toBigDecimal(total, 2)));
        assertTrue(equalsDecim(form.get(MONTHLY_REPAYMENT), toBigDecimal(monthRepayment, 2)));
        assertTrue(equalsDecim(form.get(RATE), toBigDecimal(rate, 2)));
        assertTrue(equalsDecim(form.get(REQUESTED_AMOUNT), new BigDecimal(request)));
        System.out.println(formatAll(form, OUTPUT_SEPARATOR.getSeparator()));
    }

}
