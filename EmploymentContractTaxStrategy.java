public class EmploymentContractTaxStrategy implements ITaxStrategy{

    private boolean hasDiscount = true;

    private double deductibleCoast = 250;
    private IInsuranceCommand healthInsuranceCommand;

    private SocialDuesCalculator socialDuesCalculator;

    public EmploymentContractTaxStrategy(boolean hasDiscount, double deductibleCoast, IInsuranceCommand healthInsuranceCommand, SocialDuesCalculator socialDuesCalculator) {
        this.hasDiscount = hasDiscount;
        this.deductibleCoast = deductibleCoast;
        this.healthInsuranceCommand = healthInsuranceCommand;
        this.socialDuesCalculator = socialDuesCalculator;
    }
    @Override
    public double calculate(double income) {
        DuesCalculator dues = new DuesCalculator(healthInsuranceCommand);
        FinalTaxCalculator finalTax = new FinalTaxCalculator(deductibleCoast);

        if (hasDiscount) {
            return income - dues.calculateAllTaxes(income, socialDuesCalculator) - finalTax.finalTaxCalculate(income, socialDuesCalculator);
        }
        return income - dues.calculateAllTaxes(income, socialDuesCalculator);
    }
}
