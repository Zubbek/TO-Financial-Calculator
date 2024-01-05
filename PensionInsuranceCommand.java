public class PensionInsuranceCommand implements IInsuranceCommand{

    private TaxContextStrategy taxContext;

    public PensionInsuranceCommand(TaxContextStrategy taxContext) {
        this.taxContext = taxContext;
    }

    @Override
    public double execute(double amount) {
        return amount * 0.015;
    }
}
