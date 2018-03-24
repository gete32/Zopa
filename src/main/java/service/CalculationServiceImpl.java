package service;

import entity.Lender;

import java.util.ArrayList;
import java.util.List;

public class CalculationServiceImpl implements CalculationService {

    private List<Lender> calculate(List<Lender> lenders, int target, List<Lender> partial) {
        return partial;
    }

    @Override
    public List<Lender> calculate(List<Lender> lenders, int target) {
        return calculate(lenders, target, new ArrayList<>());
    }
}
