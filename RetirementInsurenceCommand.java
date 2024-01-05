public class RetirementInsurenceCommand implements IInsuranceCommand{

    private TaxContextStrategy taxContext;

    public RetirementInsurenceCommand(TaxContextStrategy taxContext) {
        this.taxContext = taxContext;
    }

    @Override
    public double execute(double amount) {
        return amount * 0.0976;
    }
}
