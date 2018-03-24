package service;

import entity.Lender;

import java.util.List;

public interface CalculationService {

    List<Lender> calculate(List<Lender> lenders, int target);
}
