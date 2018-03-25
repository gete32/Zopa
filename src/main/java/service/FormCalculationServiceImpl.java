package service;

import constants.FormFieldsEnum;
import entity.Lender;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FormCalculationServiceImpl implements FormCalculationService {

    @Override
    public Map<FormFieldsEnum, BigDecimal> calculate(final List<Lender> lenders,
                                                     final Integer amount,
                                                     final Integer months) {
        final Map<FormFieldsEnum, BigDecimal> result = new HashMap<>();
        final Map<BigDecimal, Integer> map =
                lenders.stream()
                        .collect(Collectors.groupingBy(
                                Lender::getRate,
                                Collectors.summingInt(Lender::getAvailable)
                        ));
        final Double rate = map.entrySet().stream()
                .mapToDouble(e -> e.getKey().doubleValue() * e.getValue() / amount).sum();
        final Double paymentBase = (double) (amount / months);
        final Double totalRepayment = amount + IntStream.range(0, months).
                mapToObj(e -> amount - paymentBase * e)
                .reduce(0D, (s, a) -> s += a * rate / 12);

        result.put(FormFieldsEnum.REQUESTED_AMOUNT, BigDecimal.valueOf(amount));
        result.put(FormFieldsEnum.RATE, BigDecimal.valueOf(rate * 100));
        result.put(FormFieldsEnum.MONTHLY_REPAYMENT, BigDecimal.valueOf(totalRepayment / months));
        result.put(FormFieldsEnum.TOTAL_REPAYMENT, BigDecimal.valueOf(totalRepayment));
        return result;
    }
}
