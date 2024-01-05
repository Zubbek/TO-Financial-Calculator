public abstract class TaxDecorator implements ITaxStrategy {
    protected ITaxStrategy decoratedTaxStrategy;

    public TaxDecorator(ITaxStrategy decoratedTaxStrategy) {
        this.decoratedTaxStrategy = decoratedTaxStrategy;
    }

    public double calculate(double grossIncome) {
        return decoratedTaxStrategy.calculate(grossIncome);
    }
}
