public class TaxReliefDecorator extends TaxDecorator {
    public TaxReliefDecorator(ITaxStrategy decoratedTaxStrategy) {
        super(decoratedTaxStrategy);
    }

    @Override
    public double calculate(double income) {
        double tax = super.calculate(income);
        return tax - 500; // Przykładowa wartość ulgi podatkowej
    }
}

