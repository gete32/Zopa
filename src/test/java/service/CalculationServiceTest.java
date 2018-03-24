package service;


import entity.Lender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CalculationServiceTest extends AbstractServiceTest{

    private static CalculationService strategy;
    private static ParseService parseService;

    @BeforeAll
    static void init(){
        strategy = new CalculationServiceImpl();
        parseService = new ParseServiceImpl();
    }

    @ParameterizedTest
    @CsvSource({"testCSV.csv, 1000, 7, 30.78, 1108.10"})
    void test(String file, int request, double rate, double monthly, double total){
        Set<Lender> lenders = parseService.parse(getUrl(file).getFile());
        List<Lender> results = strategy.calculate(new ArrayList<>(lenders), request);
    }
}
