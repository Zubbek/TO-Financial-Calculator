package Command;

import Interface.IInsuranceCommand;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PensionInsuranceCommand implements IInsuranceCommand {

    double taxRate = 0.015;

    @Override
    public double execute(double amount) {
        double finaTax = amount * taxRate;
        BigDecimal bigDecimal = new BigDecimal(finaTax);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
