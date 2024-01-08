import java.math.BigDecimal;

public class RetirementInsurenceCommand implements IInsuranceCommand{

    double taxRate = 0.0976;
    @Override
    public double execute(double amount) {
       double finaTax = amount * taxRate;
        BigDecimal bigDecimal = new BigDecimal(finaTax);
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }
}
