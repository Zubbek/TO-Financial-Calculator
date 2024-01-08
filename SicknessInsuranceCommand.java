import java.math.BigDecimal;

public class SicknessInsuranceCommand  implements IInsuranceCommand{

    private double taxRate = 0.0245;
    @Override
    public double execute(double amount) {
        double finaTax = amount * taxRate;
        BigDecimal bigDecimal = new BigDecimal(finaTax);
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }
}
