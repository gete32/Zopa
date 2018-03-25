package service;

import entity.Lender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static entity.Lender.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParseServiceTest extends AbstractServiceTest {

    private static ParseService service;
    private static Map<String, Lender> lenderMap;

    @BeforeAll
    void init() {
        service = new ParseServiceImpl();
        Collection<Lender> col = new ArrayList<>();
        col.add(of("Bob", "0.075", "640"));
        col.add(of("Jane", "0.069", "480"));
        col.add(of("Fred", "0.071", "520"));
        col.add(of("Mary", "0.104", "170"));
        col.add(of("John", "0.081", "320"));
        col.add(of("Dave", "0.074", "140"));
        col.add(of("Angela", "0.071", "60"));
        lenderMap = Collections.unmodifiableMap(col.stream().collect(Collectors.toMap(Lender::getLender, Function.identity())));
    }

    @DisplayName("Testing ordering of values after parsing")
    @ParameterizedTest
    @ValueSource(strings = {"testCSV.csv"})
    void testCsvOrdering(String file) {
        List<Lender> lenders = service.parse(getUrl(file).getFile());
        for (Lender lender : lenders)
            assertEquals(lender, lenderMap.get(lender.getLender()));
    }

}
