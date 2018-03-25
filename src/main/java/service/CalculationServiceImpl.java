package service;

import entity.Summable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CalculationServiceImpl implements CalculationService<Summable> {

    private List<Sum> sums;

    private boolean calculate(Sum sum) {
        if (sum.getSum() == 0) return
                sums.add(sum);
        if (sum.getState() == 0) return false;
        if (sum.get(sum.getState() - 1).getValue() > sum.getSum()) return calculate(Sum.of(sum).remove());
        return calculate(Sum.of(sum).remove()) || calculate(Sum.of(sum).decrease());
    }

    @Override
    public List<Sum> calculate(List<Summable> values, Integer target) {
        if (values == null || values.size() == 0 || target == null) return Collections.emptyList();
        sums = new ArrayList<>();
        final Sum sum = new Sum(values.stream().sorted().collect(Collectors.toList()), target);
        calculate(sum);
        return sums;
    }

}
