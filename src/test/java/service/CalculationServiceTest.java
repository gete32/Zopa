package service;


import entity.Lender;
import entity.Summable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.platform.commons.util.StringUtils.isBlank;

class CalculationServiceTest extends AbstractServiceTest {

    private static CalculationService<Summable> strategy;
    private static ParseService parseService;

    @BeforeAll
    static void init() {
        strategy = new CalculationServiceImpl();
        parseService = new ParseServiceImpl();
    }

    @ParameterizedTest
    @CsvSource({
            "testCSV.csv, 1000, 'Jane,Fred'",
            "testCSV.csv, 660, 'Dave,Fred'",
            "testCSV.csv, 200, 'Dave,Angela'",
            "testCSV.csv, 2330, 'Mary,John,Bob,Dave,Fred,Angela,Jane'",
            "testCSV.csv, 0, ''"
    })
    void testCalculations(String file, int request, String lenderNames) {
        List<String> names = isBlank(lenderNames) ? emptyList() : asList(lenderNames.split(","));
        List<Summable> lenders = parseService.parse(getUrl(file).getFile())
                .stream()
                .sorted(Comparator.reverseOrder())
                .map(e -> (Summable) e)
                .collect(Collectors.toList());
        final List<? extends Summable> result = strategy.calculate(lenders, request);
        Assertions.assertEquals(result.size(), names.size());

        final List<String> resultLenders = result.stream().map(e -> ((Lender) e).getLender()).collect(Collectors.toList());
        for (String name : resultLenders)
            Assertions.assertTrue(names.contains(name));
    }

}
