public class ContractOfMandateTaxStrategy implements ITaxStrategy{

    private boolean hasDiscount;

    private boolean hasDues;

    private double borderlineAmount = 200;

    private double deductibleCoast;
    private IInsuranceCommand healthTax;
    private IInsuranceCommand pensionTax;

    private IInsuranceCommand retirementTax;

    private IInsuranceCommand sicknesthTax;

    private PensionTaxCommand paymentTax;


    public ContractOfMandateTaxStrategy(boolean hasDiscount, boolean hasDues, double deductibleCoast, IInsuranceCommand retirementTax, IInsuranceCommand pensionTax, IInsuranceCommand sicknesthtTax, IInsuranceCommand healthTax, PensionTaxCommand paymentTax) {
        this.hasDiscount = hasDiscount;
        this.hasDues = hasDues;
        this.deductibleCoast = deductibleCoast;
        this.retirementTax = retirementTax;
        this.pensionTax = pensionTax;
        this.sicknesthTax = sicknesthtTax;
        this.healthTax = healthTax;
        this.paymentTax = paymentTax;
    }
    @Override
    public double calculate(double income) {

        if (income <= borderlineAmount) {
            return income - (income * 0.12);
        }

        if (hasDiscount) {
            return income;
        } else if (!hasDues && !hasDiscount) {
            return income - paymentTax.execute(income);
        }
        return income - retirementTax.execute(income) - pensionTax.execute(income) - sicknesthTax.execute(income) - healthTax.execute(income)- paymentTax.execute(income);
    }
}
