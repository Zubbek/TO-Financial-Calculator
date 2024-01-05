public class TaxContextStrategy {
    private CalculationStrategy calculationStrategy;
    private List<AdditionalDecorator> additionalDecorators = new ArrayList<>();

    public void setCalculationStrategy(CalculationStrategy calculationStrategy) {
        this.calculationStrategy = calculationStrategy;
    }

    public void addDecorator(AdditionalDecorator decorator) {
        additionalDecorators.add(decorator);
    }

    public void calculate() {
        if (calculationStrategy != null) {
            calculationStrategy.calculate();

            // Zastosowanie dekoratorów
            for (AdditionalDecorator decorator : additionalDecorators) {
                decorator.decorate();
            }
        }
    }
}