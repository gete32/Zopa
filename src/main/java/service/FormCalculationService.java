package service;

import constants.FormFieldsEnum;
import entity.Lender;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FormCalculationService {
    Map<FormFieldsEnum, BigDecimal> calculate(List<Lender> lenders,
                                              Integer amount,
                                              Integer months);
}
