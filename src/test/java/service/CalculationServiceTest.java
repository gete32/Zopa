package service;


import entity.Summable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

class CalculationServiceTest extends AbstractServiceTest{

    private static CalculationService<Summable> strategy;
    private static ParseService parseService;

    @BeforeAll
    static void init(){
        strategy = new CalculationServiceImpl();
        parseService = new ParseServiceImpl();
    }

    @ParameterizedTest
    @CsvSource({"testCSV.csv, 1000, 7, 30.78, 1108.10"})
    void test(String file, int request, double rate, double monthly, double total){
        List<Summable> lenders = parseService.parse(getUrl(file).getFile())
                .stream().map(e -> (Summable) e)
                .collect(Collectors.toList());
        final List<Sum> sums = strategy.calculate(lenders, request);
    }
}
