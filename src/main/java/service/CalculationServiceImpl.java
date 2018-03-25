package service;

import entity.Summable;

import java.util.Collections;
import java.util.List;

public class CalculationServiceImpl implements CalculationService<Summable> {

    private Sum result;

    private boolean calculate(Sum sum) {
        if (sum.getSum() == 0) {
            result = sum;
            return true;
        }
        if (sum.getState() == 0) return false;
        sum.stateDecrement();
        if (sum.get(sum.getState()).getValue() > sum.getSum()) return calculate(Sum.of(sum).remove());
        return calculate(Sum.of(sum).remove()) || calculate(Sum.of(sum).decrease());
    }

    @Override
    public List<? extends Summable> calculate(List<Summable> values, Integer target) {
        if (values == null || values.size() == 0 || target == null) return null;
        result = null;
        boolean hasResult = calculate(new Sum(values, target));
        return hasResult ? result.getRest() : Collections.emptyList();
    }
}
