package service;

import entity.Summable;

import java.util.List;

public interface CalculationService<T extends Summable> {

    List<Sum> calculate(List<T> values, Integer sum);
}
