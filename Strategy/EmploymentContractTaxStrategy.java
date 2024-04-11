package Strategy;

import Interface.IInsuranceCommand;
import Interface.ITaxStrategy;

public class EmploymentContractTaxStrategy implements ITaxStrategy {

    private boolean hasDiscount;

    private double deductibleCoast;
    private IInsuranceCommand healthTax;
    private IInsuranceCommand pensionTax;

    private IInsuranceCommand retirementTax;

    private IInsuranceCommand sicknesthTax;

    private IInsuranceCommand paymentthTax;


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
