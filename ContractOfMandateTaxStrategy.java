public class ContractOfMandateTaxStrategy implements ITaxStrategy{

    private boolean hasDiscount = false;

    private double deductibleCoast = 250;

    private IInsuranceCommand iInsuranceCommand;

    private SocialDuesCalculator socialDuesCalculator;

    public ContractOfMandateTaxStrategy(boolean hasDiscount, double deductibleCoast, IInsuranceCommand iInsuranceCommand, SocialDuesCalculator socialDuesCalculator) {
        this.hasDiscount = hasDiscount;
        this.deductibleCoast = deductibleCoast;
        this.iInsuranceCommand = iInsuranceCommand;
        this.socialDuesCalculator = socialDuesCalculator;
    }
    @Override
    public double calculate(double income) {
        DuesCalculator dues = new DuesCalculator(iInsuranceCommand);
        FinalTaxCalculator finalTax = new FinalTaxCalculator(deductibleCoast);

        if (hasDiscount) {
            return income;
        }
        return income - dues.calculateAllTaxes(income, socialDuesCalculator) - finalTax.finalTaxCalculate(income, socialDuesCalculator);
    }

}