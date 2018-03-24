import entity.Lender;
import service.CalculationService;
import service.CalculationServiceImpl;
import service.ParseService;
import service.ParseServiceImpl;
import util.FormatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Calculate {

    public static void main(String[] args){
        ParseService service = new ParseServiceImpl();
        CalculationService calculationService = new CalculationServiceImpl();
        Set<Lender> lenders = service.parse(args[0]);
        FormatUtil.toInteger(args[1]);
        List<Lender> calculated = calculationService.calculate(new ArrayList<>(lenders), 1000);
//        String result = FormatUtil.formatAll(calculated, SeparatorEnum.OUTPUT_SEPARATOR.getSeparator());
//        System.out.println(result);
    }

}
