package Command;

import Interface.IInsuranceCommand;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SicknessInsuranceCommand  implements IInsuranceCommand {

    @Override
    public double execute(double amount) {
        double taxRate = 0.0245;
        double finaTax = amount * taxRate;
        BigDecimal bigDecimal = new BigDecimal(finaTax);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
