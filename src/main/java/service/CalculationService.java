package service;

import entity.Summable;

import java.util.List;

public interface CalculationService<T extends Summable> {

    List<? extends Summable> calculate(List<T> values, Integer sum);

}
