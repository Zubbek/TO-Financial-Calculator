package Strategy;

import Interface.ITaxStrategy;

public class VatCalculateTaxStrategy implements ITaxStrategy {

    private double taxPercent = 0;

    private boolean isNetto;

    public VatCalculateTaxStrategy(double taxPercent, boolean isNetto)  {
        this.taxPercent = taxPercent;
        this.isNetto = isNetto;
    }

    @Override
    public double calculate(double income) {
        if(isNetto) {
            return income + (income * taxPercent);
        }
        return income - (income * taxPercent);
    }

    public void setTaxState(double taxState) {
        this.taxPercent = taxState;
    }
}
