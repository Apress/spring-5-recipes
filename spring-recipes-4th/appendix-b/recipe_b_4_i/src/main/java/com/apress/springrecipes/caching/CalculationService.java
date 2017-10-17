package com.apress.springrecipes.caching;

import java.math.BigDecimal;

/**
 * Created by marten on 15-08-14.
 */
public interface CalculationService {

    BigDecimal heavyCalculation(BigDecimal base, int power);
}
