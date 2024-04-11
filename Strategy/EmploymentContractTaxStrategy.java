package Strategy;

import Interface.IInsuranceCommand;
import Interface.ITaxStrategy;

public class EmploymentContractTaxStrategy implements ITaxStrategy {

    private final boolean hasDiscount;

    private final double deductibleCoast;
    private final IInsuranceCommand healthTax;
    private final IInsuranceCommand pensionTax;

    private final IInsuranceCommand retirementTax;

    private final IInsuranceCommand sicknesthTax;

    private final IInsuranceCommand paymentthTax;


    public EmploymentContractTaxStrategy(boolean hasDiscount, double deductibleCoast, IInsuranceCommand retirementTax, IInsuranceCommand paymentTax, IInsuranceCommand sicknesthtTax, IInsuranceCommand healthTax, IInsuranceCommand pensionTax) {
        this.hasDiscount = hasDiscount;
        this.deductibleCoast = deductibleCoast;
        this.retirementTax = retirementTax;
        this.paymentthTax = paymentTax;
        this.sicknesthTax = sicknesthtTax;
        this.healthTax = healthTax;
        this.pensionTax = pensionTax;
    }
    @Override
    public double calculate(double income) {

        if (hasDiscount) {
            return income - retirementTax.execute(income) - paymentthTax.execute(income) - sicknesthTax.execute(income) - healthTax.execute(income);
        }
        return income - retirementTax.execute(income) - paymentthTax.execute(income) - sicknesthTax.execute(income) - healthTax.execute(income) - pensionTax.execute(income);
    }
}
