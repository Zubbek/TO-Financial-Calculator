public class FinalTaxCalculator {

    private double deductibleCoast = 250;


    public FinalTaxCalculator(double deductibleCoast) {
        this.deductibleCoast = deductibleCoast;
    }

    public double finalTaxCalculate(double amount, SocialDuesCalculator socialDuesCalculator) {
        return (amount - socialDuesCalculator.calculateAllDues(amount) - deductibleCoast) * 0.12;
    }

    public void setDeductibleCoast(double deductibleCoast) {
        this.deductibleCoast = deductibleCoast;
    }
}

