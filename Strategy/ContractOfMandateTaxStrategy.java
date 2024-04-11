package Strategy;

import Command.PensionTaxCommand;
import Interface.IInsuranceCommand;
import Interface.ITaxStrategy;

public class ContractOfMandateTaxStrategy implements ITaxStrategy {

    private final boolean hasDiscount;

    private final boolean hasDues;

    private final IInsuranceCommand healthTax;
    private final IInsuranceCommand pensionTax;

    private final IInsuranceCommand retirementTax;

    private final IInsuranceCommand sicknesthTax;

    private final PensionTaxCommand paymentTax;


    public ContractOfMandateTaxStrategy(boolean hasDiscount, boolean hasDues, double deductibleCoast, IInsuranceCommand retirementTax, IInsuranceCommand pensionTax, IInsuranceCommand sicknesthtTax, IInsuranceCommand healthTax, PensionTaxCommand paymentTax) {
        this.hasDiscount = hasDiscount;
        this.hasDues = hasDues;
        this.retirementTax = retirementTax;
        this.pensionTax = pensionTax;
        this.sicknesthTax = sicknesthtTax;
        this.healthTax = healthTax;
        this.paymentTax = paymentTax;
    }
    @Override
    public double calculate(double income) {

        double borderlineAmount = 200;
        if (income <= borderlineAmount) {
            double specialTaxRate = 0.12;
            return income - (income * specialTaxRate);
        }

        if (hasDiscount) {
            return income;
        } else if (!hasDues) {
            return income - paymentTax.execute(income);
        }
        return income - retirementTax.execute(income) - pensionTax.execute(income) - sicknesthTax.execute(income) - healthTax.execute(income)- paymentTax.execute(income);
    }
}
