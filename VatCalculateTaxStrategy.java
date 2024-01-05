public class VatCalculateTaxStrategy implements ITaxStrategy{

    private double taxState = 0;
    @Override
    public double calculate(double income) {
        return income * taxState;
    }

    public void setTaxState(double taxState) {
        this.taxState = taxState;
    }
}
